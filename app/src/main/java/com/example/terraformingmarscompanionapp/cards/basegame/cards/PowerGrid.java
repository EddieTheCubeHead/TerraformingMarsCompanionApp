package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class PowerGrid extends Card {
    public PowerGrid(Game game) {
        super("green");
        name = "Power grid";
        price = 18;
        tags.add("energy");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(player.getEnergyTags());
        super.onPlay(player);
    }
}
