package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

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
