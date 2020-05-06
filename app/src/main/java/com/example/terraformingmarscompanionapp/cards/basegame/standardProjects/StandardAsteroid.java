package com.example.terraformingmarscompanionapp.cards.basegame.standardProjects;

import com.example.terraformingmarscompanionapp.cardSubclasses.StandardProject;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class StandardAsteroid extends StandardProject {
    public StandardAsteroid(Game game) {
        super(game);
        name = "Standard project: Asteroid";
        price = 14;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.raiseTemperature(player);
        super.playWithMetadata(player, data);
    }
}
