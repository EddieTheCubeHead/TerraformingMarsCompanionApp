package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;

import java.util.ArrayList;

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
     * A method to calculate the points a player has for this award. Override with an algorithm
     * specific to the award in question
     *
     * @param player {@link Player} the points are calculated for
     * @return {@link Integer} the score for the given {@link Player}
     */
    public abstract Integer pointCriteria(Player player);

    /**
     * A method that calculates the award winner and runner-up and increases their victory points by
     * 5 and 2 respectively.
     */
    private void getAwardResult() {
        ArrayList<Player> first_place = new ArrayList<>();
        ArrayList<Player> second_place = new ArrayList<>();

        Integer max = 0;
        Integer second = 0;

        for (Player player : GameController.getPlayers()) {
            Integer player_result = pointCriteria(player);
            if (player_result > max) {
                second = max;
                max = player_result;
                second_place = first_place;
                first_place.clear();
                first_place.add(player);
            } else if (player_result.equals(max)) {
                first_place.add(player);
                second_place.clear();
                second = max;
            } else if (player_result > second && first_place.size() < 2) {
                second = player_result;
                second_place.clear();
                second_place.add(player);
            } else if (player_result.equals(second) && first_place.size() <2) {
                second_place.add(player);
            }
        }

        for (Player player : first_place) {
            player.setVictoryPoints(player.getVictoryPoints() + 5);
        }

        if (GameController.getPlayers().size() == 2) {
            return;
        }

        for (Player player : second_place) {
            player.setVictoryPoints(player.getVictoryPoints() + 2);
        }
    }

    @Override
    public final void onGameEnd() {
        if (claimed) {
            getAwardResult();
        }
    }
}
