package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class MagneticDomeField extends Card {
    public MagneticDomeField(Game game) {
        name = "Magnetic dome field";
        price = 5;
        tags.put("building", 1);
        requirements.put("min_energy_production", 2);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addBuildingTag();
        player.changeEnergyProduction(-2);
        player.changePlantsProduction(1);
        player.changeTerraformingRating(1);
        player.addGreen(this);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
