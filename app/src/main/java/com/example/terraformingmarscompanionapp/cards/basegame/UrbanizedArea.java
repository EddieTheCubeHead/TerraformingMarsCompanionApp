package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class UrbanizedArea extends Card {
    public UrbanizedArea(Game game) {
        name = "Urbanized area";
        price = 10;
        tags.put("city", 1);
        tags.put("building", 1);
        requirements.put("min_energy_production", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addBuildingTag();
        player.addCityTag();
        player.changeEnergyProduction(-1);
        player.changeMoneyProduction(2);
        player.addGreen(this);
        owner_player = player;
        while (true) {
            if (owner_game.tile_handler.placeUrbanizedArea(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
            }
        }
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
