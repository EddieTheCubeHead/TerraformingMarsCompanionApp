package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class CarbonateProcessing extends Card {
    public CarbonateProcessing(Game game) {
        name = "Carbonate processing";
        price = 6;
        tags.put("building", 1);
        requirements.put("min_energy_production", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addBuildingTag();
        player.changeEnergyProduction(-1);
        player.changeHeatProduction(3);
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
