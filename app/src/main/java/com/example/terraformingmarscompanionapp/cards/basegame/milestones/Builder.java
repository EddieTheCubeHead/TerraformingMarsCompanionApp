package com.example.terraformingmarscompanionapp.cards.basegame.milestones;

import com.example.terraformingmarscompanionapp.game.cardClasses.Milestone;
import com.example.terraformingmarscompanionapp.game.Game;

public final class Builder extends Milestone {
    public Builder() {
        super();
        name = "Builder";
        requirements.setMinBuildingTags(8);
    }
}
