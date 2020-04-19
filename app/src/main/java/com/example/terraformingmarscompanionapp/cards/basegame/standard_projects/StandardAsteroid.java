package com.example.terraformingmarscompanionapp.cards.basegame.standard_projects;

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
    public Integer onPlay(Player player) {
        owner_game.raiseTemperature(player);
        return super.onPlay(player);
    }
}
