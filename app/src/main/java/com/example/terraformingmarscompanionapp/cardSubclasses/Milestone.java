package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

/**
 * A subclass of {@link Card} representing the milestone gameplay system of the game.
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public abstract class Milestone extends Card {

    /**
     * A constuctor to ease the creation of milestones
     *
     * @param game {@link Game} the card is associated with
     */
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
