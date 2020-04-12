package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class NuclearZone extends Card {
    public NuclearZone(Game game) {
        super("green");
        name = "Nuclear zone";
        price = 10;
        tags.add("earth");
        victory_points = -2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeNuclearZone(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollinen peruminen
            }
        }
        owner_game.raiseTemperature(player);
        owner_game.raiseTemperature(player);
        super.onPlay(player);
    }
}
