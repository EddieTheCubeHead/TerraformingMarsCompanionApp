package com.example.terraformingmarscompanionapp.cards.basegame.milestones;

import com.example.terraformingmarscompanionapp.game.cardClasses.Milestone;
import com.example.terraformingmarscompanionapp.game.Game;

public final class Gardener extends Milestone {
    public Gardener() {
        super();
        name = "Gardener";
        requirements.setMinPersonalGreeneries(3);
    }
}
