package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

/**
 * A subclass of {@code Card} for creating the different awards offered by different maps
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public abstract class Award extends Card {
    private Boolean claimed = false;

    /**
     * Overriding the constructor for QoL reasons
     *
     * @param game {@link Game} the award is associated with
     */
    public Award(Game game) {
        super(Type.AWARD, game);
        owner_game = game;
        requirements.setMaxAwardsClaimed(2);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.claimAward();
        claimed = true;
        super.playWithMetadata(player, data);
    }

    @Override
    public final Integer getPrice() {
        return 8 + (owner_game.getClaimedAwards() * 6);
    }

    /**
     * A method that calculates the award winner and runner-up and increases their victory points by
     * 5 and 2 respectively. Used to make creating new awards easy with {@link #onGameEnd()}
     */
    public abstract void getAwardResult();

    @Override
    public final void onGameEnd() {
        if (claimed) {
            getAwardResult();
        }
    }
}
