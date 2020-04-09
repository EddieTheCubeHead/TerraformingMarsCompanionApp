package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

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
