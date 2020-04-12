package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class PeroxidePower extends Card {
    public PeroxidePower(Game game) {
        super("green");
        name = "Peroxide power";
        price = 7;
        tags.add("energy");
        tags.add("building");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeMoneyProduction(-1);
        player.changeEnergyProduction(2);
        super.onPlay(player);
    }
}
