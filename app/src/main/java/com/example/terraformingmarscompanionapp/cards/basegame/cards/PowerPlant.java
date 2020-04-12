package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class PowerPlant extends Card {
    public PowerPlant(Game game) {
        super("green");
        name = "Power plant";
        price = 4;
        tags.add("energy");
        tags.add("building");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(1);
        super.onPlay(player);
    }
}
