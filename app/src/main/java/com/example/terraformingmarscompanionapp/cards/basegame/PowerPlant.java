package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class PowerPlant extends Card {
    public PowerPlant(Game game) {
        name = "Power plant";
        price = 4;
        tags.put("energy", 1);
        tags.put("building", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(1);
        player.addEnergyTag();
        player.addBuildingTag();
        player.addGreen(this);
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction(Player player) {
        return false;
    }
}
