package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Mangrove extends Card {
    public Mangrove(Game game) {
        super("green");
        name = "Mangrove";
        price = 12;
        tags.add("plant");
        requirements.setMinTemperature(4);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeOceanGreenery(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
            }
        }
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
