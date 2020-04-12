package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class SolarWindPower extends Card {
    public SolarWindPower(Game game) {
        super("green");
        name = "Solar wind power";
        price = 11;
        tags.add("science");
        tags.add("space");
        tags.add("energy");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(1);
        player.changeTitanium(2);
        super.onPlay(player);
    }
}
