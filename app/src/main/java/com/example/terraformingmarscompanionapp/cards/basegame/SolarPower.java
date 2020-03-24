package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class SolarPower extends Card {
    public SolarPower(Game game) {
        name = "Solar power";
        price = 11;
        tags.put("energy", 1);
        tags.put("building", 1);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addEnergyTag();
        player.addBuildingTag();
        player.changeEnergyProduction(1);
        player.addGreen(this);
        owner_player = player;
        owner_game.updateManager.onVpCardPlayed(player);
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
