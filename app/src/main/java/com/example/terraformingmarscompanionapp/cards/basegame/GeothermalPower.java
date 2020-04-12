package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class GeothermalPower extends Card {
    public GeothermalPower(Game game) {
        super("green");
        name = "Geothermal power";
        price = 11;
        tags.add("building");
        tags.add("energy");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(2);
        super.onPlay(player);
    }
}
