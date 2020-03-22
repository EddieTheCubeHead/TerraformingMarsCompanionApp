package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class FusionPower extends Card {
    public FusionPower(Game game) {
        name = "Fusion power";
        price = 14;
        tags.put("science", 1);
        tags.put("energy", 1);
        tags.put("building", 1);
        requirements.put("min_energy_tags", 2);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(3);
        player.addScienceTag();
        player.addEnergyTag();
        player.addBuildingTag();
        player.addGreen(this);
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction(Player player) {
        return false;
    }
}
