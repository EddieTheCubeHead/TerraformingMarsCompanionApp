package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public abstract class Award extends Card {
    protected Boolean claimed = false;

    public Award(Game game) {
        super(Type.AWARD);
        owner_game = game;
        requirements.setMaxAwardsClaimed(2);
    }

    @Override
    public final Integer onPlay(Player player) {
        owner_game.claimAward();
        return super.onPlay(player);
    }

    @Override
    public final Integer getPrice() {
        return 8 + (owner_game.getClaimedAwards() * 6);
    }

    public abstract void getAwardResult();

    @Override
    public final void onGameEnd() {
        getAwardResult();
    }
}
