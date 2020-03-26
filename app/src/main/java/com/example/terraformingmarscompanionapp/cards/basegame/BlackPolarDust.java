package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class BlackPolarDust extends Card {
    public BlackPolarDust(Game game) {
        name = "Black polar dust";
        price = 15;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeMoneyProduction(-2);
        player.changeHeatProduction(3);
        owner_game.placeOcean(player, false);
        player.addGreen(this);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
