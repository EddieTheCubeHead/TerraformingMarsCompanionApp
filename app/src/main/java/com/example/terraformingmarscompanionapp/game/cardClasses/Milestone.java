package com.example.terraformingmarscompanionapp.game.cardClasses;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

/**
 * A subclass of {@link Card} representing the milestone gameplay system of the game.
 *
 * @author Eetu Asikainen
 * @version 0.3
 * @since 0.2
 */
public abstract class Milestone extends Card {

    /**
     * A constuctor to ease the creation of milestones
     */
    public Milestone() {
        super(Type.MILESTONE);
        requirements.setMaxMilestonesClaimed(2);
        victory_points = 5;
        price = 8;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        game.claimMilestone();
        super.playWithMetadata(player, data);
    }
}
