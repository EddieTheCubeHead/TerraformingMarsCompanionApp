package com.example.terraformingmarscompanionapp.game;


import android.content.Context;
import android.util.Log;

import com.example.terraformingmarscompanionapp.InGameUI;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.FirstAction;
import com.example.terraformingmarscompanionapp.game.events.GameEvent;

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

    private Deque<GameEvent> ui_events = new LinkedList<>();
    public void addUiEvent(GameEvent event) {ui_events.addLast(event);}
    public Boolean executeNextEvent() {
        if (ui_events.size() == 0) {
            return false;
        }
        GameEvent event = ui_events.removeFirst();
        event.playEvent();
        return true;
    }

    //Context ingameui:n löytämiseen ja setteri
    private Context context = null;
    public void setContext(Context context) {
        this.context = context;
    }

    //Gettereitä yleisesti käytetyille muuttujille
    public Game getGame() { return game; }
    public Player getCurrentPlayer()  { return current_player; }
    public Player getCurrentStarter() { return current_starter; }
    public Context getContext() { return context; }

    //Serveripeliä varten tarkistus onko clientin vuoro, vai jonkun muun
    public Boolean checkTurnEligibility() {
        if (!server_multiplayer) {
            return true;
        } else if (self_player == null) {
            Log.i("GameController", "Moninpelilogiikkavirhe vuorojen tarkkailussa, huomauta Eetua.");
            return false;
        }
        return current_player==self_player;
    }

    public Player getPlayer(Integer index) {return queue_full.get(index - 1);}

    //Player player = new Player(game, "Testipelaaja");

    private GameController(Game game){
        this.game = game;

        ArrayList<Player> players = game.getPlayers();
        if (players == null || players.size() == 0)
            new Exception().printStackTrace();

        queue_full.addAll(players);
        queue.addAll(queue_full);

        current_player = queue.getFirst();
        current_starter = current_player;
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

    public void makeMultiplayer(Player player) {
        server_multiplayer=true;
        self_player = player;
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

    //vuorojen hallitseminen
    private Boolean folding = false;
    public void setPlayerIsFolding(Boolean currentIsFolding) { folding = currentIsFolding; }
    public void foldOnTurnEnd() { folding = true; }

    //Toiminnon käyttäminen
    public void useAction() {
        actions_used++;
        if (actions_used >= 2) {
            endTurn();
        }
    }

    public void endTurn()
    {
        beforeTurnEnd();

        if(actions_used == 0)
            folding = true;

        //vuoron vaihto
        if (folding)
            queue.removeFirst();
        else
            queue.addLast(queue.removeFirst());
        setPlayerIsFolding(false);

        //kun kaikki on foldannu
        if (queue.size() == 0)
        {
            endGeneration();
        }

        current_player = queue.getFirst();

        ((InGameUI)context).onTurnChange(current_player.getName());
        atTurnStart();
    }

    private void beforeTurnEnd()
    {
        //TODO kaikki vuoron lopussa vuoron lopettavalle current_playerille tapahtuva
    }

    private void atTurnStart()
    {
        actions_used = 0;
        gameUpdate();
        if (current_player.getCorporation() instanceof FirstAction) {
            FirstAction action = (FirstAction)current_player.getCorporation();
            if (!action.firstActionUsed()) {
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
    }

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
        Log.i("Game", "Update called");
        for (GameUpdateListener listener : game_listeners) {
            listener.update();
        }
    }

    //Kaikkien pelaajien getteri
    public List<Player> getPlayers()
    {
        return queue_full;
    }

    //TODO tokenien sijoittaminen
}
