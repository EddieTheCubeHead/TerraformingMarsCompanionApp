package com.example.terraformingmarscompanionapp.cards.basegame.milestones;

import com.example.terraformingmarscompanionapp.game.cardClasses.Milestone;
import com.example.terraformingmarscompanionapp.game.Game;

public final class Mayor extends Milestone {
    public Mayor() {
        super();
        name = "Mayor";
        requirements.setMinPersonalCities(3);
    }
}
