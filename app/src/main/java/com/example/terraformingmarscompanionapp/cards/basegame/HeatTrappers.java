package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class HeatTrappers extends Card {
    public HeatTrappers(Game game) {
        name = "Heat trappers";
        price = 6;
        tags.put("energy", 1);
        tags.put("building", 1);
        victory_points = -1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addEnergyTag();
        player.addBuildingTag();
        //TODO poista toiselta kaksi lämpöä
        player.changeEnergyProduction(1);
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
