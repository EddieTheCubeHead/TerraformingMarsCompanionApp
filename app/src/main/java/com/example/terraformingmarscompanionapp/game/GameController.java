package com.example.terraformingmarscompanionapp.game;


import android.content.Context;

import com.example.terraformingmarscompanionapp.InGameUI;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.FirstAction;
import com.example.terraformingmarscompanionapp.cards.basegame.corporations.BeginnerCorporation;
import com.example.terraformingmarscompanionapp.game.events.GameEvent;
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Olio jonka pitäisi sisältää kaikki UI-luokkien kutsuma joka liittyy pelilogiikkaan.
 * -Hallinnoi vuoroja, sukupolvia.
 */


public class GameController
{
    //dequessa vuorojärjestys. ensimmäinen jäsen on aina nykyinen
    //queue sisältää kaikki jotka eivät ole foldannu
    static GameController instance = null;

    private Integer generation = 0;

    public Integer getGeneration() {return generation;}
    public Integer getDisplayActions() {
        return 2 - actions_used;
    }

    private Game game;
    private List<Player> queue_full = new ArrayList<>(); //double ended queue
    private Deque<Player> queue = new LinkedList<>();
    private Player current_player;
    private Player current_starter;

    //Seuraa käytettyjen toimintojen määrää, nollataan aina kun pelaaja vaihtuu
    private Integer actions_used = 0;

    //Controllerin pitää tietää onko peli serverin välityksellä pelattu, ja jos on, kuka pelaajista on kyseisen clientin omistaja
    private Boolean server_multiplayer = false;
    private Player self_player;

    //Pelejä ei sinällään hostata yhdellä clientillä, mutta "host" synkronoi sukupolvenvaihdot GameActions.sendGenerationChange() avulla
    private Boolean is_host = false;
    public void makeHost() {is_host = true;}

    //UI-event jono asynkronisesti kutsuttaviin UI-tapahtumiin (tile, resource, cost(?) ja cardprompt)
    private Deque<GameEvent> ui_events = new LinkedList<>();
    private Boolean delay_action_use = false;
    public void addUiEvent(GameEvent event) {ui_events.addLast(event);}
    private Boolean executeNextEvent() {
        if (ui_events.size() == 0) {
            return false;
        }
        System.out.println("Delay action use set");
        delay_action_use = true;
        GameEvent event = ui_events.removeFirst();
        event.playEvent();
        return true;
    }

    //Context ingameui:n löytämiseen ja setteri
    private Context context = null;
    public void setContext(Context context) {
        this.context = context;
    }

    //Serveripeliä varten instanssin omistaja
    public void setSelfPlayer(Player player) {
        self_player = player;
    }

    //Gettereitä yleisesti käytetyille muuttujille
    public Game getGame() { return game; }
    public Player getCurrentPlayer()  { return current_player; }
    public Context getContext() { return context; }

    //Serveripeliä varten tarkistus onko clientin vuoro, vai jonkun muun
    public Boolean checkTurnEligibility() {
        if (!server_multiplayer) {
            return true;
        } else if (self_player == null) {
            return false;
        }
        return current_player==self_player;
    }

    public Player getPlayer(Integer index) {return queue_full.get(index - 1);}

    public Player getPlayer(String name)
    {
        for (Player p : queue_full)
        {
            if (p.getName() == name)
                return p;
        }
        return null;
    }

    public Integer getPlayerIndex(Player player) {return (queue_full.indexOf(player)+1);}

    private GameController(Game game){
        this.game = game;

        ArrayList<Player> players = game.getPlayers();
        if (players == null || players.size() == 0)
            new Exception().printStackTrace();

        queue_full.addAll(players);
        queue.addAll(queue_full);

        current_player = queue.getFirst();
        current_starter = current_player;

        if (game.getServerMultiplayer()) {
            server_multiplayer = true;
        }
        //oletuksena:
        // ensimmäisen sukupolven aloittaja on laittanut nimensä ekana.
        // tästä jatketaan nimien laittamisjärjestyksessä.
        //voi muuttaa vapaasti.
    }

    public static GameController makeInstance(Game game)
    {
        if (instance != null)
        {
            System.out.println("GAMECONTROLLER ALREADY INSTANCIATED BEFORE");
            new Exception().printStackTrace();
            System.exit(-1);
        }
        instance = new GameController(game);
        return instance;
    }

    public static GameController getInstance()
    {
        if (instance == null)
        {
            System.out.println("GAMECONTROLLER NOT INSTANTIATED AT GETINSTANCE");
            new Exception().printStackTrace();
            System.exit(-1);
        }
        return instance;
    }

    //Toiminnon käyttäminen
    public Boolean useAction() {
        if (executeNextEvent()) {
            gameUpdate();
            return false;
        }
        if (delay_action_use) {
            delay_action_use = false;
            if (server_multiplayer) {
                GameActions.sendUseAction();
            }
        }
        actions_used++;
        if (actions_used >= 2) {
            endTurn();
        }

        gameUpdate();

        return true;
    }

    public void useActionServer() {
        ui_events.clear();
        useAction();
    }

    public void endTurn()
    {

        if(actions_used == 0)
            queue.removeFirst();
        else
            queue.addLast(queue.removeFirst());

        //kun kaikki on foldannu
        if (queue.size() == 0)
        {
            endGeneration();
        } else {
            current_player = queue.getFirst();
            atTurnStart();
        }
    }

    public void atTurnStart()
    {
        System.out.println("Calling turn start function, GameController row 205");
        actions_used = 0;
        gameUpdate();

        System.out.println(current_player.getName() + " " + generation);

        //Aloituskierroksen toimenpiteet
        if (generation == 0) {
            System.out.println("Generation 0 actions, GameController row 211");
            if (self_player == null || current_player == self_player) {
                if (current_player.getCorporation() == null) {
                    ((InGameUI) context).playCorporation();
                } else if (game.modifiers.getPrelude() && current_player.getPreludes().size() == 0) {
                    ((InGameUI) context).playPreludes();
                } else {
                    changeGeneration();
                }
            }

        //Kierroksen alun kortinnosto
        } else if (!current_player.getDrewCardsThisGen() && (self_player == null || current_player == self_player)) {
            System.out.println("Card draw, GameController row 224");

            //Beginner corporation nostaa ensimmäisellä kierroksella kymmenen korttia ilmaiseksi
            if (current_player.getCorporation() instanceof BeginnerCorporation && generation == 1) {
                GameActions.sendUseAction();
                useAction();
                current_player.changeHandSize(10);
                current_player.setDrewCardsThisGen(true);
                addUiEvent(new PromptEvent(current_player.getName() + ", please draw 10 cards."));
                useAction();
            } else {
                if (server_multiplayer) {
                    GameActions.sendUseAction();
                }
                game.getDeck().get("Round start draw").onPlay(current_player);
            }

        //Korporaatioiden määrittämät ensimmäiset toiminnot, jos on olemassa
        } else if (generation == 1 && (self_player == null || current_player == self_player) && current_player.getCorporation() instanceof FirstAction)

        {
            FirstAction action = (FirstAction)current_player.getCorporation();

            if (!action.firstActionUsed())
            {
                action.firstAction();
                useAction();
            }
        }
    }

    public void endGeneration()
    {
        game.onGenerationEnd();

        //epäfoldaus
        queue.clear();
        queue.addAll(queue_full);

        //seuraavaan aloittajaan vaihto
        while(current_starter != queue.getFirst())
            queue.addLast(queue.removeFirst());
        queue.addLast(queue.removeFirst());

        current_starter = queue.getFirst();
        current_player = current_starter;
        ((InGameUI)context).onGenerationEnd();

        changeGeneration();
    }

    public void changeGeneration()
    {
        if (!server_multiplayer) {
            atGenerationStart();
        } else if (is_host) {
            GameActions.sendChangeGeneration();
        }
    }

    public void atGenerationStart()
    {
        generation += 1;
        atTurnStart();
    }
    public Player getSelfPlayer() {return self_player;}

    public Player getDisplayPlayer()
    {
        Player display_player;

        //display_player on se jonka kortit näytetään päänäytöllä.
        if (server_multiplayer)
            display_player = self_player;
        else
            display_player = getCurrentPlayer();

        return display_player;
    }

    public ArrayList<Card> getCards(Player player)
    {
        ArrayList<Card> deck = new ArrayList<>();

        deck.addAll(player.getBlue());
        deck.addAll(player.getGreen());
        deck.addAll(player.getRed());

        return deck;
    }

    public ArrayList<Card> getCards()
    {
        ArrayList<Card> deck = new ArrayList<>();

        Player subject = getDisplayPlayer();

        deck.addAll(subject.getBlue());
        deck.addAll(subject.getGreen());
        deck.addAll(subject.getRed());

        return deck;
    }

    //https://stackoverflow.com/questions/37759734/dynamically-updating-a-fragment/37761276#37761276
    //Fragmenttien päivittämiseen
    public interface GameUpdateListener {
        void update();
    }

    private List<GameUpdateListener> game_listeners = new ArrayList<>();

    public synchronized void registerGameUpdateListener(GameUpdateListener listener) {
        game_listeners.add(listener);
    }

    public synchronized void unregisterGameUpdateListener(GameUpdateListener listener) {
        game_listeners.remove(listener);
    }

    public void gameUpdate() {
        for (GameUpdateListener listener : game_listeners) {
            listener.update();
        }
    }

    //Kaikkien pelaajien getteri
    public List<Player> getPlayers()
    {
        return queue_full;
    }

    public void endGame() {

    }

    public void promptUser(String text) {
        ((InGameUI)context).displayPrompt(text);
    }

    //TODO tokenien sijoittaminen
}
