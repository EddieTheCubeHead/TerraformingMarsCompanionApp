package com.example.terraformingmarscompanionapp.cards.basegame.awards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Award;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

import java.util.ArrayList;

public final class Scientist extends Award {
    public Scientist(Game game) {
        super(game);
        name = "Scientist";
    }

    @Override
    public void getAwardResult() {
        ArrayList<Player> first_place = new ArrayList<>();
        ArrayList<Player> second_place = new ArrayList<>();

        Integer max = 0;
        Integer second = 0;

        for (Player player : owner_game.getPlayers()) {
            Integer player_result = player.getScienceTags();
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
            player.changeVictoryPoints(5);
        }

        if (owner_game.getPlayers().size() == 2) {
            return;
        }

        for (Player player : second_place) {
            player.changeVictoryPoints(2);
        }
    }
}
