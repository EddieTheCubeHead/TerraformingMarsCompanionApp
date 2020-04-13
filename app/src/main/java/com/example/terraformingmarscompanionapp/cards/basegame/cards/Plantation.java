package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Plantation extends Card {
    public Plantation(Game game) {
        super("green");
        name = "Plantation";
        price = 15;
        tags.add("plant");
        requirements.setMinScienceTags(2);
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
        player.addGreenery();
        super.onPlay(player);
    }
}
