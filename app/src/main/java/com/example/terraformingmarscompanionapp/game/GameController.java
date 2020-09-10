package com.example.terraformingmarscompanionapp.game;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.terraformingmarscompanionapp.InGameUi;
import com.example.terraformingmarscompanionapp.game.cardClasses.Award;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.FirstAction;
import com.example.terraformingmarscompanionapp.cards.basegame.corporations.BeginnerCorporation;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.GameEvent;
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.ui.gameMainElements.InGameActivity;
import com.example.terraformingmarscompanionapp.ui.main.GameResultActivity;
import com.example.terraformingmarscompanionapp.ui.main.GameUiElement;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.packets.ActionUsePacket;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * The class responsible for the main part of ingame logic via turn order and calling the automatic
 * actions depending on turn order. Is static to faciliate easy calling from anywhere.
 * <p></p>
 * Houses a weak reference to the current UI {@link Context} that should be updated every time a
 * context changes. Many actions all over the codebase use this context for playing{@link GameEvent#playEvent(Context)}
 * so it is important it is the current context.
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public class GameController
{

    // Generation and action info
    private static Integer generation = 0;
    private static Integer action_number = 0;

    public static Integer getGeneration() {
        return generation;
    }

    public static Integer getActionNumber() {
        return action_number;
    }

    public static Integer getDisplayActions() {
        return 2 - actions_used;
    }

    private static Game game;
    private static List<Player> players = new ArrayList<>();
    private static Deque<Player> queue = new LinkedList<>(); //double ended queue
    private static Player current_player;
    private static Player current_starter;
    private static Boolean greenery_round = false;

    private static Integer actions_used = 0;

    // Controller needs to know if the game is played via a server and if so, which player is the
    // one playing on this instance.
    private static Boolean server_multiplayer = false;
    private static Player self_player;

    // Games aren't hosted on a single client. The "host" merely sends generation end messages to
    // enable more accurate syncing of generation changes
    private static Boolean is_host = false;

    // Weak reference to current context
    private static WeakReference<Context> context_reference;

    // UI classes that require updating with game logic
    // TODO have a look at whether this is storing Contextes in a static class and whether it is really bad
    private static List<GameUpdateListener> game_listeners = new ArrayList<>();

    // Storing the gamestates in a stack for undo
    private static Deque<byte[]> game_states = new LinkedList<>();

    // Maz size of the undo list
    private static final Integer MAX_UNDO_SIZE = 10;

    /**
     * A simple method to set the owner of this game instance
     *
     * @param player {@link Player} playing on this client in a server-based game
     */
    public static void setSelfPlayer(Player player) {
        self_player = player;
    }

    /**
     * A method to set the UI context the game is currently running in. Should get called whenever
     * the UI context changes with the fresh context
     *
     * @param context {@link Context} the UI context the game is currently running in
     */
    public static void setContextReference(Context context) {
        context_reference = new WeakReference<>(context);
    }

    /**
     * A method to get the current context of the game. Can return null and usage should be limited
     * to places where getting context from a passed parameter is impossible.
     *
     * @return {@link Context} the context the game is currently running in
     */
    public static Context getContext() {
        if (context_reference != null) {
            return context_reference.get();
        }
        return null;
    }

    /**
     * @return {@link Game} that is associated with this instance of the app
     */
    public static Game getGame() {
        return game;
    }

    /**
     * @return {@link Player} whose turn it is
     */
    public static Player getCurrentPlayer()  {
        return current_player;
    }

    /**
     * @return {@link List} of {@link Player} containing all players in the game
     */
    public static List<Player> getPlayers()
    {
        return players;
    }

    /**
     * A method to check if the client's commands are valid from turn-perspective. Returns always
     * true if the game is played in hotseat mode. In a server-based game returns true if it is
     * client's turn
     *
     * @return {@link Boolean} whether an action from client is valid to play right now
     */
    public static Boolean checkTurnEligibility() {
        if (!server_multiplayer) {
            return true;
        } else if (self_player == null) {
            return false;
        }
        return current_player==self_player;
    }

    /**
     * A method to get a player based on their index in the player list. Note that the index is
     * "natural" and not computer-based and starts from 1. This way 0 can be used as empty metadata
     * for playing cards that indicate their target with the target index
     *
     * @param index {@link Integer} indicating the player's index in the player list, starting from 1
     * @return {@link Player} corresponding to the given index
     */
    public static Player getPlayer(Integer index) {
        if (index == 0) {
            Log.i("GameController", "CAUTION: getPlayer(index) called with index of 0!");
            return null;
        }
        return players.get(index - 1);
    }

    /**
     * A method to get a player based on their name
     *
     * @param name {@link String} the name of the wanted player
     * @return {@link Player} corresponding to the given name
     */
    public static Player getPlayer(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                Log.i("GameController", "Found player matching name '" + name + "'");
                return player;
            } else {
                Log.i("GameController", "Looking for player '" + name + "' didn't match player '" + player.getName() + "'.");
            }
        }
        return null;
    }

    /**
     * A method to get an index for a given player based on their name. Indexing starts from 1 to
     * make passing 0 as metadata mean no target for target-dependent cards.
     *
     * @param player_name {@link String} the name of the wanted player
     * @return {@link Integer} the index of the given player, starting from 1
     */
    public static Integer getPlayerIndex(String player_name) {
        Player player = getPlayer(player_name);
        return (players.indexOf(player)+1);
    }

    /**
     * A method to set the parameters of the class when starting the game
     *
     * @param game {@link Game} that is associated with this instance of the app
     * @param is_host {@link Boolean} indicating whether this instance of the app is the host of a server-based game
     * @param player_names {@link ArrayList} of {@link String} containing the names of all players in the game in the wanted turn order
     */
    public static void initGameController(Game game, Boolean is_host, @NotNull ArrayList<String> player_names) {
        GameController.game = game;

        if (player_names.size() == 0) {
            new Exception().printStackTrace();
        }

        for (String player_name : player_names) {
            players.add(new Player(game, player_name));
        }

        queue.addAll(players);

        current_player = queue.getFirst();
        current_starter = current_player;

        if (game.getServerMultiplayer()) {
            server_multiplayer = true;
            GameController.is_host = is_host;
        }
    }

    /**
     * @return {@link Boolean} whether the game is played via a server
     */
    public static Boolean getServerMultiplayer() {
        return server_multiplayer;
    }

    /**
     * A method to increment the actions used by the current player.
     *
     * @param end_turn {@link Boolean} whether the current turn should be ended after incrementing the value
     */
    public static void useAction(Boolean end_turn) {
        actions_used++;
        gameUpdate();

        if (actions_used >= 2 || end_turn) {
            endTurn();
        }
    }

    /**
     * A method to end the current turn and perform all required logic
     *
     * @param context {@link Context} the context from which this action is called from
     */
    public static void endTurn(Context context)
    {

        if (actions_used == 0)
            queue.removeFirst();
        else
            queue.addLast(queue.removeFirst());

        // If everyone has folded
        if (queue.size() == 0)
        {
            Log.i("GameController", "Player queue empty. Ending generation");
            endGeneration(context);
        } else {
            current_player = queue.getFirst();
            atTurnStart(context);
        }
    }

    /**
     * An overload for {@link #endTurn(Context)} to use when there is not direct access to a fresh
     * {@link Context}. Use should be avoided as this uses a static weak reference to a context and
     * therefor is more prone to bugs than the version taking fresh context as a parameter
     */
    public static void endTurn() {
        endTurn(getContext());
    }

    /**
     * A method for checking all automatic actions that have flags that occur on the start of a
     * player's turn
     *
     * @param context {@link Context} the UI Context this method is called from
     */
    public static void atTurnStart(Context context)
    {
        actions_used = 0;

        gameUpdate();

        Log.i("Game controller", String.format("At turn start called for player %s, generation %s", current_player, generation));

        // Sometimes there are specific actions at the start of a generation or at certain generations
        // this if-else mess keeps track of those

        // Last generation is only for placing greeneries. No card draw
        if (greenery_round) {
            return;
        }

        // Preparation round
        if (generation == 0) {
            if (self_player == null || current_player == self_player) {
                if (current_player.getCorporation() == null) {
                    ((InGameActivity) context).playCorporation();
                } else if (game.modifiers.getPrelude() && current_player.getPlayedPreludes()) {
                    ((InGameActivity) context).playPreludes();
                }
            }

        // Activity at the start of the round
        } else if (!current_player.getDrewCardsThisGen() && (self_player == null || current_player == self_player)) {

            current_player.setDrewCardsThisGen(true);

            // Beginner corporation draws 10 cards for free at game start
            if (current_player.getCorporation() instanceof BeginnerCorporation && generation == 1) {
                EventScheduler.addEvent(new ActionUseEvent(new ActionUsePacket(true, true)));
                current_player.changeHandSize(10);
                current_player.setDrewCardsThisGen(true);
                EventScheduler.addEvent(new PromptEvent(current_player.getName() + ", please draw 10 cards."));
                EventScheduler.playNextEvent(context);


            } else {
                EventScheduler.addEvent(new ActionUseEvent(new ActionUsePacket(true, true)));
                game.getDeck().get("Round start draw").initializePlayEvents(current_player);
                EventScheduler.playNextEvent(context);
            }

        // First actions declared by corporation cards
        } else if (generation == 1 && (self_player == null || current_player == self_player) && current_player.getCorporation() instanceof FirstAction)

        {
            FirstAction action = (FirstAction)current_player.getCorporation();

            if (!action.firstActionUsed())
            {
                Log.i("Game Controller", "Calling first action for player " + current_player.getName());
                action.firstAction();
            }
        }
    }

    /**
     * A method that starts the generation changing process. Hosts the turn order logic and calls
     * {@link Game#onGenerationEnd(Context)} as the next part of the generation change chain.
     *
     * @param context {@link Context} the UI Context this method is called from
     */
    private static void endGeneration(Context context)
    {
        if (greenery_round) {
            Log.i("GameController", "Calling point counting function to end game");
            countPoints();
            return;
        }

        queue.clear();
        queue.addAll(players);
        System.out.println(queue.toString());

        if (generation > 0) {
            Log.i("GameController", "Assigning new turn order for generation " + (generation + 1));
            while (current_starter != queue.getFirst())
                queue.addLast(queue.removeFirst());
            queue.addLast(queue.removeFirst());

            current_starter = queue.getFirst();
            ((InGameUi) context).generationEndPrompt();
        }
        current_player = current_starter;

        game.onGenerationEnd(context);
    }

    /**
     * A method that is part of the generation changing chain. Responsible for synchronizing the
     * generation changing in a server-based game by either ending the chain if the client is not
     * the "host" or sending a packet that tells other clients to continue the chain if the client
     * is the "host".
     *
     * @param context {@link Context} the UI Context this method is called from
     */
    static void syncGenerationChange(Context context)
    {
        if (!server_multiplayer) {
            atGenerationStart(context);
        } else if (is_host) {
            GameActions.sendChangeGeneration();
        }
    }

    /**
     * The last method of the generation ending chain. Mainly just used as the second part of syncing
     * a generation.
     *
     * @param context {@link Context} the UI Context this method is called from
     */
    private static void atGenerationStart(Context context)
    {
        Log.i("Game Controller", "Generation start called");
        generation += 1;
        atTurnStart(context);
    }

    /**
     * An overload for {@link #atGenerationStart(Context)} to use when there is not direct access to a fresh
     * {@link Context}. Use should be avoided as this uses a static weak reference to a context and
     * therefor is more prone to bugs than the version taking fresh context as a parameter
     */
    public static void atGenerationStart() {
        atGenerationStart(getContext());
    }

    /**
     * @return {@link Player} the player corresponding to this client in a server-based game
     */
    public static Player getSelfPlayer() {
        if (server_multiplayer) {
            return self_player;
        } else {
            Log.i("GameController", "getSelfPlayer called in hotseat!");
            return null;
        }
    }

    /**
     * @return {@link Player} the player whose stats should be displayed at the moment. Current player in hotseat or self player in server-based game
     */
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

    /**
     * A small method to aid with ending a game. Enables the final round of placing greeneries
     */
    static void gameEndPreparation() {
        greenery_round = true;
    }

    /**
     * A method to count player victory points at the end of a game
     */
    private static void countPoints() {
        for (Award award : game.getAwards().values()) {
            award.onGameEnd();
        }

        for (Card card : game.getAllCards().values()) {
            if (card.getOwner() != null) {
                card.onGameEnd();
            }
        }

        for (Player player : players) {
            player.countPoints();
        }

        Intent result_intent = new Intent(getContext(), GameResultActivity.class);
        Log.i("GameController", "Calling result screen");
        getContext().startActivity(result_intent);
    }

    /**
     * @return {@link Boolean} whether the last round of playing greeneries is in effect
     */
    public static Boolean getGreeneryRound() {
        return greenery_round;
    }

    /**
     * A method to save the current state of the game for undoing
     *
     * @throws IOException
     */
    public static void saveGame() throws IOException {

        ByteArrayOutputStream byte_out = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byte_out);

        GameState state = new GameState(game, players, current_player, current_starter, actions_used, generation, action_number);

        out.writeObject(state);
        out.flush();

        game_states.add(byte_out.toByteArray());

        Log.i("GameController", "Gamestate saved, list size: " + game_states.size());

        if (game_states.size() > MAX_UNDO_SIZE) {
            Log.i("GameController", "Gamestate list over max allowed size, removing oldest state");
            game_states.removeFirst();
        }
    }

    /**
     * A method for loading the game from a saved gamestate
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void loadGame() throws IOException, ClassNotFoundException {

        if (game_states.isEmpty()) {
            promptUser("Cannot undo further!", getContext());
            return;
        }

        ByteArrayInputStream byte_in = new ByteArrayInputStream(game_states.removeLast());
        ObjectInputStream in = new ObjectInputStream(byte_in);

        GameState state = (GameState) in.readObject();

        Log.i("GameController", "Deserializing gamestate from GameState-object");
        game = state.getGame();
        players = state.getPlayers();
        current_player = getPlayer(state.getCurrentPlayerName());
        current_starter = getPlayer(state.getCurrentStarterName());
        actions_used = state.getActionsUsed();
        generation = state.getGeneration();
        action_number = state.getActionNumber();

        gameUpdate();
    }


    // https://stackoverflow.com/questions/37759734/dynamically-updating-a-fragment/37761276#37761276
    /**
     * A method to update all UI elements that require knowledge of game state
     */
    public interface GameUpdateListener {
        void update();
    }

    /**
     * A method to add an UI element as a listener to game state changes
     *
     * @param listener {@link GameUpdateListener} a UI element implementing the interface
     */
    public static synchronized void registerGameUpdateListener(GameUpdateListener listener) {

        // Avoiding duplicates
        if (game_listeners.contains(listener)) {
            Log.i("GameController", "CAUTION: attempting to re-register listener: " + listener);
            return;
        }

        game_listeners.add(listener);
        Log.i("GameController", "Registered an update listener: " + listener);
    }

    // TODO find out if irrelevant, maybe replace with emptying the listener list at game end?
    /**
     * A method to remove an UI element as a listener to game state changes
     *
     * @param listener {@link GameUpdateListener} a UI element implementing the interface
     */
    public static synchronized void unregisterGameUpdateListener(GameUpdateListener listener) {
        game_listeners.remove(listener);
    }

    /**
     * A method to update all registered update listeners
     */
    static void gameUpdate() {
        Log.i("GameController", "Updating UI");
        for (GameUpdateListener listener : game_listeners) {
            listener.update();
        }
    }

    /**
     * A small method to display a toast prompt for the player
     *
     * @param text {@link String} the message to be displayed
     * @param context {@link Context} the context to display the message in
     */
    public static void promptUser(String text, Context context) {
        ((InGameActivity)context).displayPrompt(text);
    }
}
