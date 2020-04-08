package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class PermafrostExtraction extends Card {
    public PermafrostExtraction(Game game) {
        super("red");
        name = "Permafrost extraction";
        price = 8;
        tags.add("event");
        requirements.put("min_temperature", -8);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeOcean(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus peruuttaa
            }
        }
        super.onPlay(player);
    }
}
