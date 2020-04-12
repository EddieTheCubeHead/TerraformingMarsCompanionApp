package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class FueledGenerators extends Card {
    public FueledGenerators(Game game) {
        super("green");
        name = "Fueled generators";
        price = 1;
        tags.add("energy");
        tags.add("building");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeMoneyProduction(-1);
        player.changeEnergyProduction(1);
        super.onPlay(player);
    }
}
