package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class DomedCrater extends Card {
    public DomedCrater(Game game) {
        name = "Domed crater";
        price = 24;
        tags.put("building", 1);
        tags.put("city", 1);
        requirements.put("max_oxygen", 7);
        requirements.put("min_energy_production", 1);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addBuildingTag();
        player.addCityTag();
        player.changePlants(3);
        player.changeEnergyProduction(-1);
        player.changeMoneyProduction(3);
        owner_game.placeCity(player, 0);
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
