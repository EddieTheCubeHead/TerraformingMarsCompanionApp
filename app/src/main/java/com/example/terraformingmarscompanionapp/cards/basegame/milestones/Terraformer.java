package com.example.terraformingmarscompanionapp.cards.basegame.milestones;

import com.example.terraformingmarscompanionapp.cardSubclasses.Milestone;
import com.example.terraformingmarscompanionapp.game.Game;

public final class Terraformer extends Milestone {
    public Terraformer(Game game) {
        super(game);
        name = "Terraformer";
        requirements.setMinTr(35);
        //TODO turmoil-muutos
    }
}
