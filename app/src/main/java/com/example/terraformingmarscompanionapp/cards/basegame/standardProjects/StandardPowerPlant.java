package com.example.terraformingmarscompanionapp.cards.basegame.standardProjects;

import com.example.terraformingmarscompanionapp.game.cardClasses.StandardProject;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class StandardPowerPlant extends StandardProject {
    public StandardPowerPlant() {
        super();
        name = "Standard project: Power plant";
        price = 11;
        tags.add(Tag.ENERGY);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getResources().setEnergyProduction(player.getResources().getEnergyProduction() + 1);
        super.playWithMetadata(player, data);
    }
}
