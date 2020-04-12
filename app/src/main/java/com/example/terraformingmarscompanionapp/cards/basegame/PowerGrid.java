package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

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
