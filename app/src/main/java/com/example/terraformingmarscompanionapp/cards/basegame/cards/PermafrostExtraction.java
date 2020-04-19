package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class PermafrostExtraction extends Card {
    public PermafrostExtraction(Game game) {
        super(Type.RED);
        name = "Permafrost extraction";
        price = 8;
        tags.add(Tag.EVENT);
        requirements.setMinTemperature(-8);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeOcean(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus peruuttaa
            }
        }
        return super.onPlay(player);
    }
}
