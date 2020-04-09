package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class IndustrialMicrobes extends Card {
    public IndustrialMicrobes(Game game) {
        super("green");
        name = "Industrial microbes";
        price = 12;
        tags.add("microbe");
        tags.add("building");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeSteelProduction(1);
        player.changeEnergyProduction(1);
        super.onPlay(player);
    }
}
