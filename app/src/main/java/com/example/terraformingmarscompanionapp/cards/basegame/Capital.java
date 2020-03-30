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
        player.changeEnergyProduction(-2);
        player.changeMoneyProduction(5);
        owner_player = player;
        owner_game.updateManager.onVpCardPlayed(player);

        while (true) {
            if (owner_game.tile_handler.placeCapital(player)) {
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
