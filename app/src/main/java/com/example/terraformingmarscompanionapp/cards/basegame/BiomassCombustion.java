package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class BiomassCombustion extends Card {
    public BiomassCombustion(Game game) {
        name = "Biomass combustion";
        price = 4;
        tags.put("energy", 1);
        tags.put("building", 1);
        requirements.put("min_oxygen", 6);
        victory_points = -1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addEnergyTag();
        player.addBuildingTag();
        //TODO poista toiselta kasvi
        player.changeEnergyProduction(2);
        player.addGreen(this);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
