package com.example.terraformingmarscompanionapp.cards.basegame.awards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Award;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Scientist extends Award {
    public Scientist(Game game) {
        super(game);
        name = "Scientist";
    }

    @Override
    public Integer pointCriteria(Player player) {
        return player.getTags().getScienceTags();
    }
}
