package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class MagneticDomeField extends Card {
    public MagneticDomeField(Game game) {
        super("green");
        name = "Magnetic dome field";
        price = 5;
        tags.add("building");
        requirements.put("min_energy_production", 2);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(-2);
        player.changePlantsProduction(1);
        player.changeTerraformingRating(1);
        super.onPlay(player);
    }
}
