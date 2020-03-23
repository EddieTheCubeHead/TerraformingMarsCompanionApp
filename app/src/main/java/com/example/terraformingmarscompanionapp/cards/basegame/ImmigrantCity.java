package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class ImmigrantCity extends Card {
    public ImmigrantCity(Game game) {
        name = "Immigrant city";
        price = 13;
        tags.put("city", 1);
        tags.put("building", 1);
        requirements.put("min_energy_production", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addCityTag();
        player.addBuildingTag();
        player.changeMoneyProduction(-2);
        player.changeEnergyProduction(-1);
        owner_game.placeCity(player, 0);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {
        owner_player.changeMoneyProduction(1);
    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
