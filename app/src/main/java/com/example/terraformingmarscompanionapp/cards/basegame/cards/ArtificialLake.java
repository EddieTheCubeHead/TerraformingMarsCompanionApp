package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class ArtificialLake extends Card {
    public ArtificialLake(Game game) {
        super("green");
        name = "Artificial lake";
        price = 15;
        tags.add("building");
        requirements.setMinTemperature(-6);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeLandOcean(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
            }
        }
        owner_game.update_manager.onVpCardPlayed(player);

        super.onPlay(player);
    }
}
