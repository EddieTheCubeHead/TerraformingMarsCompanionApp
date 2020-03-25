package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class NoctisCity extends Card {
    public NoctisCity(Game game) {
        name = "Noctis city";
        price = 18;
        tags.put("city", 1);
        tags.put("building", 1);
        requirements.put("min_energy_production", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addCityTag();
        player.addBuildingTag();
        player.changeEnergyProduction(-1);
        player.changeMoneyProduction(3);
        player.addGreen(this);
        owner_game.placeCity(player, 2);
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
