package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class ProtectedValley extends Card {
    public ProtectedValley(Game game) {
        super(Type.GREEN);
        name = "Protected valley";
        price = 23;
        tags.add(Tag.PLANT);
        tags.add(Tag.BUILDING);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeOceanGreenery(player)) {
                break;
            } else {
                //TODO feedback pelaajalla ja mahdollisuus peruuttaa
            }
        }
        player.changeMoneyProduction(2);
        player.addGreenery();
        return super.onPlay(player);
    }
}
