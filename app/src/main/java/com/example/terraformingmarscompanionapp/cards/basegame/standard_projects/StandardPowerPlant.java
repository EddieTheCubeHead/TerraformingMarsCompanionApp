package com.example.terraformingmarscompanionapp.cards.basegame.standard_projects;

import com.example.terraformingmarscompanionapp.CardSubclasses.StandardProject;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class StandardPowerPlant extends StandardProject {
    public StandardPowerPlant(Game game) {
        super(game);
        name = "Standard project: Power plant";
        price = 14;
        tags.add("energy");
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(1);
        super.onPlay(player);
    }
}
