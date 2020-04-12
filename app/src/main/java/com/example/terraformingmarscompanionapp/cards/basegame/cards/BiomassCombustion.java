package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class BiomassCombustion extends Card {
    public BiomassCombustion(Game game) {
        super("green");
        name = "Biomass combustion";
        price = 4;
        tags.add("energy");
        tags.add("building");
        requirements.put("min_oxygen", 6);
        victory_points = -1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        //TODO poista toiselta kasvi
        player.changeEnergyProduction(2);
        super.onPlay(player);
    }
}
