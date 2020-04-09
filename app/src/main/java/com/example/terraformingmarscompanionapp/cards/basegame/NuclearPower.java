package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class NuclearPower extends Card {
    public NuclearPower(Game game) {
        super("green");
        name = "Nuclear power";
        price = 10;
        tags.add("energy");
        tags.add("building");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeMoneyProduction(-2);
        player.changeEnergyProduction(3);
        super.onPlay(player);
    }
}
