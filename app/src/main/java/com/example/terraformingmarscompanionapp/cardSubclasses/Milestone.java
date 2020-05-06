package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public abstract class Milestone extends Card {
    private Boolean claimed = false;

    public Milestone(Game game) {
        super(Type.MILESTONE, game);
        requirements.setMaxMilestonesClaimed(2);
        victory_points = 5;
        price = 8;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.claimMilestone();
        super.playWithMetadata(player, data);
    }
}
