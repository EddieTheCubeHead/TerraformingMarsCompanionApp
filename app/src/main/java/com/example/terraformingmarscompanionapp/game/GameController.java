package com.example.terraformingmarscompanionapp.game;


import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.InGameUI;
import com.example.terraformingmarscompanionapp.cardSubclasses.Award;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.FirstAction;
import com.example.terraformingmarscompanionapp.cards.basegame.corporations.BeginnerCorporation;
import com.example.terraformingmarscompanionapp.game.events.GameEvent;
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;
import com.example.terraformingmarscompanionapp.ui.main.GameUiElement;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.packets.ActionUsePacket;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * The main logic class of the app. Responsible mainly for managing players' turn order and
 * queueing up UI-events based on committed  actions.
 */


public class GameController
{
    //Horrible but deadline came too soon.
    static GameController instance = null;

    //Generation and action info
    private static Integer generation = 0;
    private static Integer action_number = 0;

    public static Integer getGeneration() {return generation;}
    public static Integer getActionNumber() {return action_number;}
    public static Integer getDisplayActions() {
        return 2 - actions_used;
    }

    private static Game game;
    private static List<Player> queue_full = new ArrayList<>();
    private static Deque<Player> queue = new LinkedList<>(); //double ended queue
    private static Player current_player;
    private static Player current_starter;
    private static Boolean greenery_round = false;

    private static Integer actions_used = 0;

    //Controller needs to know if the game is played via a server and if so, which player is the one playing on this instance
    private static Boolean server_multiplayer = false;
    private static Player self_player;

    //Games aren't hosted on a single client. The "host" merely sends generation end messages to keep that in sync.
    private static Boolean is_host = false;

    //Setting the instance owner
    public static void setSelfPlayer(Player player) {
        self_player = player;
    }

    //Weak reference to InGameUI activity
    private static WeakReference<Context> context_reference;
    public static void setContextReference(Context context) {context_reference = new WeakReference<>(context);}
    public static Context getContext() {
        if (context_reference != null) {
            return context_reference.get();
        }
        return null;
    }

    public static Game getGame() { return game; }
    public static Player getCurrentPlayer()  { return current_player; }

    //Checking if it is client's turn in a server game
    public static Boolean checkTurnEligibility() {
        if (!server_multiplayer) {
            return true;
        } else if (self_player == null) {
            return false;
        }
        return current_player==self_player;
    }

    public static Player getPlayer(Integer index) {return queue_full.get(index - 1);}

    public static Player getPlayer(String name)
    {
        for (Player p : queue_full)
        {
            if (p.getName() == name)
                return p;
        }
        return null;
    }

    public static Integer getPlayerIndex(String player_name) {
        Player player = game.getPlayer(player_name);
        return (queue_full.indexOf(player)+1);
    }

    //TODO find out what needs to go here. Can basically work as a constructor
    public static void initGameController(Game game, Boolean is_host) {
        GameController.game = game;

        ArrayList<Player> players = game.getPlayers();
        if (players == null || players.size() == 0) {
            new Exception().printStackTrace();
        }

        queue_full.addAll(players);
        queue.addAll(queue_full);

        current_player = queue.getFirst();
        current_starter = current_player;

        if (game.getServerMultiplayer()) {
            server_multiplayer = true;
            GameController.is_host = is_host;
        }
    }

    public static Boolean getServerMultiplayer() {return server_multiplayer;}

    //These should be gone after the remake

    /*
    //Working with a single instance makes life easier
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
    } */

    public static void useAction(Boolean end_turn) {
        actions_used++;

        if (actions_used >= 2 || end_turn) {
            endTurn();
        }
    }

    public static void endTurn() {endTurn(getContext());}
    public static void endTurn(Context context)
    {

        if (actions_used == 0)
            queue.removeFirst();
        else
            queue.addLast(queue.removeFirst());

        //kun kaikki on foldannu
        if (queue.size() == 0)
        {
            endGeneration(context);
        } else {
            current_player = queue.getFirst();
            atTurnStart(context);
        }
    }

    public static void atTurnStart(Context context)
    {
        actions_used = 0;
        gameUpdate();

        System.out.println(current_player.getName() + " " + generation);

        //Sometimes there are specific actions at the start of a generation or at certain generations
        //this if-else mess keeps track of those

        //Last generation is only for placing greeneries. No card draw
        if (greenery_round) {
            return;
        }

        //Preparation round
        if (generation == 0) {
            if (self_player == null || current_player == self_player) {
                if (current_player.getCorporation() == null) {
                    ((InGameUI) context).playCorporation();
                } else if (game.modifiers.getPrelude() && current_player.getPreludes().size() == 0) {
                    ((InGameUI) context).playPreludes();
                } else {
                    changeGeneration(context);
                }
            }

        //Activity at the start of the round
        } else if (!current_player.getDrewCardsThisGen() && (self_player == null || current_player == self_player)) {

            //Beginner corporation draws 10 cards for free at game start
            if (current_player.getCorporation() instanceof BeginnerCorporation && generation == 1) {
                if (server_multiplayer) {
                    GameActions.sendActionUse(new ActionUsePacket(false));
                }
                current_player.changeHandSize(10);
                current_player.setDrewCardsThisGen(true);
                EventScheduler.addEvent(new PromptEvent(current_player.getName() + ", please draw 10 cards."));

            } else {
                game.getDeck().get("Round start draw").onPlay(current_player, context);
            }

        //First actions declared by corporation cards
        } else if (generation == 1 && (self_player == null || current_player == self_player) && current_player.getCorporation() instanceof FirstAction)

        {
            FirstAction action = (FirstAction)current_player.getCorporation();

            if (!action.firstActionUsed())
            {
                action.firstAction();
            }
        }
    }

    private static void endGeneration(Context context)
    {
        if (greenery_round) {
            countPoints();
            return;
        }

        queue.clear();
        queue.addAll(queue_full);

        while(current_starter != queue.getFirst())
            queue.addLast(queue.removeFirst());
        queue.addLast(queue.removeFirst());

        current_starter = queue.getFirst();
        current_player = current_starter;
        ((InGameUI)context).generationEndPrompt();

        game.onGenerationEnd(context);
    }

    //Small function to keep server game in sync. Basically ignored in hotseat
    static void changeGeneration(Context context)
    {
        if (!server_multiplayer) {
            atGenerationStart(context);
        } else if (is_host) {
            GameActions.sendChangeGeneration();
        }
    }

    // Other part of the generation syncing. Called from change generation in hotseat,
    // or via a websocketevent in server game
    public static void atGenerationStart() {atGenerationStart(getContext());}
    public static void atGenerationStart(Context context)
    {
        generation += 1;
        atTurnStart(context);
    }

    //Player getters concerning turn order
    public static Player getSelfPlayer() {return self_player;}

    public static Player getDisplayPlayer()
    {
        Player display_player;

        //Current player in hotseat, client in server game
        if (server_multiplayer)
            display_player = self_player;
        else
            display_player = getCurrentPlayer();

        return display_player;
    }

    //Game ending logic
    static void gameEndPreparation() {
        greenery_round = true;
    }

    private static void countPoints() {
        for (Award award : game.getAwards().values()) {
            award.onGameEnd();
        }

        for (Card card : game.getAllCards().values()) {
            if (card.getOwner() != null) {
                card.onGameEnd();
            }
        }

        for (Player player : queue_full) {
            player.countPoints();
            System.out.println(player.getName() + ", points: " + player.getVictoryPoints());
        }
    }

    public static Boolean getGreeneryRound() {return greenery_round;}


    //https://stackoverflow.com/questions/37759734/dynamically-updating-a-fragment/37761276#37761276
    //Upgrading necessary fragments
    public interface GameUpdateListener {
        void update();
    }

    private static List<GameUpdateListener> game_listeners = new ArrayList<>();

    public static synchronized void registerGameUpdateListener(GameUpdateListener listener) {
        game_listeners.add(listener);
    }

    //TODO find out if irrelevant, maybe replace with emptying the listener list at game end?
    public static synchronized void unregisterGameUpdateListener(GameUpdateListener listener) {
        game_listeners.remove(listener);
    }

    static void gameUpdate() {
        for (GameUpdateListener listener : game_listeners) {
            listener.update();
        }
    }

    //Sometimes easier to call this from here, than from game
    public static List<Player> getPlayers()
    {
        return queue_full;
    }

    public static void promptUser(String text, Context context) {
        ((GameUiElement)context).displayPrompt(text);
    }
}
