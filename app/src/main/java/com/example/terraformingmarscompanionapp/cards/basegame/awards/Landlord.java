package com.example.terraformingmarscompanionapp.cards.basegame.awards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Award;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Landlord extends Award {
    public Landlord() {
        super();
        name = "Landlord";
    }

    @Override
    public Integer pointCriteria(Player player) {
        return player.getOwnedTiles().size();
    }
}
