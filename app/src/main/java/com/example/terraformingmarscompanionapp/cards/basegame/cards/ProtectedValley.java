package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class ProtectedValley extends Card {
    public ProtectedValley(Game game) {
        super("green");
        name = "Protected valley";
        price = 23;
        tags.add("plant");
        tags.add("building");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeOceanGreenery(player)) {
                break;
            } else {
                //TODO feedback pelaajalla ja mahdollisuus peruuttaa
            }
        }
        player.changeMoneyProduction(2);

        super.onPlay(player);
    }
}
