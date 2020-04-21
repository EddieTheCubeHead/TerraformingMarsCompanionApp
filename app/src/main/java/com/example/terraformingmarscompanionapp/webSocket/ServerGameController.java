package com.example.terraformingmarscompanionapp.webSocket;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

import java.util.ArrayList;

public class ServerGameController {
    private static ArrayList<String> players = new ArrayList<>();
    private static Player self = null;

    public static void addPlayer(String player_name) {
        players.add(player_name);
    }

    public static GameController startGame(Boolean corporate_era, Boolean prelude, Boolean colonies, Boolean venus, Boolean turmoil, Boolean extra_corporations, Integer map) {
        Game game = new Game(players,
                corporate_era, prelude, colonies, venus, turmoil, extra_corporations, true,
                map);

        self = game.getPlayer(UserActions.getSessionUser());
        GameController controller = GameController.makeInstance(game);
        controller.makeMultiplayer();
        return controller;
    }
}
