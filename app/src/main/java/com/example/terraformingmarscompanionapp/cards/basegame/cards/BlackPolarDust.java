package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class BlackPolarDust extends Card {
    public BlackPolarDust(Game game) {
        super("green");
        name = "Black polar dust";
        price = 15;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeMoneyProduction(-2);
        player.changeHeatProduction(3);
        while (true) {
            if (owner_game.tile_handler.placeOcean(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
            }
        }
        super.onPlay(player);
    }
}
