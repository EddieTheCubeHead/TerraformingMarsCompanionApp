package com.example.terraformingmarscompanionapp.cards.basegame.milestones;

import com.example.terraformingmarscompanionapp.game.cardClasses.Milestone;
import com.example.terraformingmarscompanionapp.game.Game;

public final class Planner extends Milestone {
    public Planner() {
        super();
        name = "Planner";
        requirements.setMinCardsInHand(16);
    }
}
