package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

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
