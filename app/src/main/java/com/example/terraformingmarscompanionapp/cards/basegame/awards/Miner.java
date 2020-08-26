package com.example.terraformingmarscompanionapp.cards.basegame.awards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Award;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Miner extends Award {
    public Miner() {
        super();
        name = "Miner";
    }

    @Override
    public Integer pointCriteria(Player player) {
        return (player.getResources().getSteel() + player.getResources().getTitanium());
    }
}
