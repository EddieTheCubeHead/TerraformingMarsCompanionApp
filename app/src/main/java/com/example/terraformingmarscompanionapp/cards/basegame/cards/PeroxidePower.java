package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

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
