package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Plantation extends Card {
    public Plantation(Game game) {
        super("green");
        name = "Plantation";
        price = 15;
        tags.add("plant");
        requirements.put("min_science_tags", 2);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeGreenery(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus perua
            }
        }
        super.onPlay(player);
    }
}
