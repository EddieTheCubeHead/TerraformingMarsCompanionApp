package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class RadChemFactory extends Card {
    public RadChemFactory(Game game) {
        super("green");
        name = "Rad-chem factory";
        price = 8;
        tags.add("building");
        requirements.put("min_energy_production", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(-1);
        player.changeTerraformingRating(2);
        super.onPlay(player);
    }
}
