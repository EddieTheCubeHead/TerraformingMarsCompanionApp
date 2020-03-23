package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Capital extends Card {
    public Capital(Game game) {
        name = "Capital";
        price = 26;
        tags.put("building", 1);
        tags.put("city", 1);
        requirements.put("min_oceans", 4);
        requirements.put("min_energy_production", 2);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addBuildingTag();
        player.addCityTag();
        owner_game.placeCity(player, 1);
        player.changeEnergyProduction(-2);
        player.changeMoneyProduction(5);
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
