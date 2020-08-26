package com.example.terraformingmarscompanionapp.cards.basegame.milestones;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Milestone;
import com.example.terraformingmarscompanionapp.game.Game;

public final class Terraformer extends Milestone {
    public Terraformer() {
        super();
        name = "Terraformer";
        requirements.setMinTr(Card.game.modifiers.getTurmoil() ? 25 : 35);
    }
}
