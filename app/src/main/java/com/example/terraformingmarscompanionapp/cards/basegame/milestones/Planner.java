package com.example.terraformingmarscompanionapp.cards.basegame.milestones;

import com.example.terraformingmarscompanionapp.cardSubclasses.Milestone;
import com.example.terraformingmarscompanionapp.game.Game;

public final class Planner extends Milestone {
    public Planner(Game game) {
        super(game);
        name = "Planner";
        requirements.setMinCardsInHand(16);
    }
}
