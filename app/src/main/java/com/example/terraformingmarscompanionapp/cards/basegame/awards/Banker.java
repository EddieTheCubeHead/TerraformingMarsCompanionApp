package com.example.terraformingmarscompanionapp.cards.basegame.awards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Award;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Banker extends Award {
    public Banker() {
        super();
        name = "Banker";
    }

    @Override
    public Integer pointCriteria(Player player) {
        return player.getResources().getMoneyProduction();
    }
}
