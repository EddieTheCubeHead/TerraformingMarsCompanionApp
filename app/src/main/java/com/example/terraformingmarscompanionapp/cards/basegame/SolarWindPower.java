package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class SolarWindPower extends Card {
    public SolarWindPower(Game game) {
        name = "Solar wind power";
        price = 11;
        tags.put("science", 1);
        tags.put("space", 1);
        tags.put("energy", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addScienceTag();
        player.addSpaceTag();
        player.addEnergyTag();
        player.changeEnergyProduction(1);
        player.changeTitanium(2);
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
