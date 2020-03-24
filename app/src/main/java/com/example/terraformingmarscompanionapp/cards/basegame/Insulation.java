package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Insulation extends Card {
    public Insulation(Game game) {
        name = "Insulation";
        price = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        Integer heat_reduction_amount = 0;
        //TODO UI kysymään kuinka paljon lämmöntuotantoa poistetaan
        player.changeHeatProduction(-heat_reduction_amount);
        player.changeMoneyProduction(heat_reduction_amount);
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
