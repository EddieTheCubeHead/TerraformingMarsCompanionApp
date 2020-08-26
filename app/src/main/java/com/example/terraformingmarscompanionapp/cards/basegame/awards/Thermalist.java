package com.example.terraformingmarscompanionapp.cards.basegame.awards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Award;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Thermalist extends Award {
    public Thermalist() {
        super();
        name = "Thermalist";
    }

    @Override
    public Integer pointCriteria(Player player) {
        return player.getResources().getHeat();
    }
}
