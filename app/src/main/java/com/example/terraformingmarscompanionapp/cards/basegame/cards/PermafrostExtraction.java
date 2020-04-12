package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

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
