package com.example.terraformingmarscompanionapp.cards.basegame.standardProjects;

import com.example.terraformingmarscompanionapp.game.cardClasses.StandardProject;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class StandardAsteroid extends StandardProject {
    public StandardAsteroid() {
        super();
        name = "Standard project: Asteroid";
        price = 14;
        requirements.setMaxTemperature(6);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        game.raiseTemperature(player);
        super.playWithMetadata(player, data);
    }
}
