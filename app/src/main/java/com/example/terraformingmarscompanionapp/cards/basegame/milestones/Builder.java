package com.example.terraformingmarscompanionapp.cards.basegame.milestones;

import com.example.terraformingmarscompanionapp.cardSubclasses.Milestone;
import com.example.terraformingmarscompanionapp.game.Game;

public final class Builder extends Milestone {
    public Builder(Game game) {
        super(game);
        name = "Builder";
        requirements.setMinBuildingTags(8);
    }
}
