package com.example.terraformingmarscompanionapp.cards.basegame;

import android.graphics.Path;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class OpenCity extends Card {
    public OpenCity(Game game) {
        name = "Open city";
        price = 23;
        tags.put("building", 1);
        tags.put("city", 1);
        requirements.put("min_oxygen", 12);
        requirements.put("min_energy_production", 1);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(-1);
        player.changeMoneyProduction(4);
        player.changePlants(2);
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
