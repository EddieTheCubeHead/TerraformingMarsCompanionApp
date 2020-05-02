package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public abstract class Milestone extends Card {
    private Boolean claimed = false;

    public Milestone(Game game) {
        super(Type.MILESTONE);
        owner_game = game;
        requirements.setMaxMilestonesClaimed(2);
        victory_points = 5;
        price = 8;
    }

    @Override
    public final Integer onPlay(Player player) {
        owner_game.claimMilestone();
        owner_player = player;
        return super.onPlay(player);
    }
}
