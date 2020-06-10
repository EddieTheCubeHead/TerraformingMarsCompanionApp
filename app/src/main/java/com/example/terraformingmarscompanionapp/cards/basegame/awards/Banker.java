package com.example.terraformingmarscompanionapp.cards.basegame.awards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Award;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;

import java.util.ArrayList;

public final class Banker extends Award {
    public Banker(Game game) {
        super(game);
        name = "Banker";
    }

    @Override
    public Integer pointCriteria(Player player) {
        return player.getResources().getMoneyProduction();
    }
}
