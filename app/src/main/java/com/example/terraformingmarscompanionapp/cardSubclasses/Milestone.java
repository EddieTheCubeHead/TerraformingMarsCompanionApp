package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.Player;

public abstract class Milestone extends Card {
    public Milestone() {
        super(Type.MILESTONE);
    }

    @Override
    public Integer onPlay(Player player) {
        owner_game.claimMilestone();
        return super.onPlay(player);
    }
}
