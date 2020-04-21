package com.example.terraformingmarscompanionapp.webSocket;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;

import java.util.ArrayList;

public class ServerGameController {
    private static ArrayList<String> players = new ArrayList<>();
    public static String getPlayerName(Integer index) {return players.get(index - 1);}

    public static void addPlayer(String player_name) {
        players.add(player_name);
    }

    public static GameController startGame(Boolean corporate_era, Boolean prelude, Boolean colonies, Boolean venus, Boolean turmoil, Boolean extra_corporations, Integer map) {
        Game game = new Game(players,
                corporate_era, prelude, colonies, venus, turmoil, extra_corporations, true,
                map);

        GameController controller = GameController.makeInstance(game);
        controller.makeMultiplayer(game.getPlayer(UserActions.getSessionUser()));
        return controller;
    }
}
