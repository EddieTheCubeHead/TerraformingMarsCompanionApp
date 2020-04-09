package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Mangrove extends Card {
    public Mangrove(Game game) {
        super("green");
        name = "Mangrove";
        price = 12;
        tags.add("plant");
        requirements.put("min_temperature", 4);
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
