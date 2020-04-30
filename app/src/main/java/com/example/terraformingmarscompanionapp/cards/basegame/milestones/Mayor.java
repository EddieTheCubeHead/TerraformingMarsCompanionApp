package com.example.terraformingmarscompanionapp.cards.basegame.milestones;

import com.example.terraformingmarscompanionapp.cardSubclasses.Milestone;
import com.example.terraformingmarscompanionapp.game.Game;

public final class Mayor extends Milestone {
    public Mayor(Game game) {
        super(game);
        name = "Mayor";
        requirements.setMinPersonalCities(3);
    }
}
