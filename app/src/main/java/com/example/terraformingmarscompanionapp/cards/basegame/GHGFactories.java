package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class GHGFactories extends Card {
    public GHGFactories(Game game) {
        super("green");
        name = "GHG factories";
        price = 11;
        tags.add("building");
        requirements.put("min_energy_production", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(-1);
        player.changeHeatProduction(4);
        super.onPlay(player);
    }
}
