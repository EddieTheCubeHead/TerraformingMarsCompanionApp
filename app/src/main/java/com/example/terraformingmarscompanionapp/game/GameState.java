package com.example.terraformingmarscompanionapp.game;

import com.example.terraformingmarscompanionapp.game.player.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A class used for serializing a state of the game
 *
 * @author Eetu Asikainen
 * @version 0.3
 * @since 0.3
 */
public class GameState implements Serializable {

    private Game game;
    private List<Player> players;
    private String current_player_name;
    private String current_starter_name;
    private Integer actions_used;
    private Integer generation;
    private Integer action_number;

    /**
     * Constructor
     *
     * @param game {@link Game} the game object that needs to be saved
     * @param players {@link ArrayList} of {@link Player} objects corresponding the current state of players in the game
     * @param current_player {@link String} representing the name of the current player
     * @param current_starter {@link String} representing the name of the current generation starter
     * @param actions_used {@link Integer} representing how many actions the current player can use still
     * @param generation {@link Integer} representing the number of the current generation
     * @param action_number {@link Integer} representing the number of actions taken in the whole game
     */
    public GameState(Game game, List<Player> players, Player current_player, Player current_starter,
                     Integer actions_used, Integer generation, Integer action_number) {
        this.game = game;
        this.players = players;
        this.current_player_name = current_player.getName();
        this.current_starter_name = current_starter.getName();
        this.actions_used = actions_used;
        this.generation = generation;
        this.action_number = action_number;
    }

    // Emitting javadoc for getters for now

    public Game getGame() {
        return game;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getCurrentPlayerName() {
        return current_player_name;
    }

    public String getCurrentStarterName() {
        return current_starter_name;
    }

    public Integer getActionsUsed() {
        return actions_used;
    }

    public Integer getGeneration() {
        return generation;
    }

    public Integer getActionNumber() {
        return action_number;
    }
}
