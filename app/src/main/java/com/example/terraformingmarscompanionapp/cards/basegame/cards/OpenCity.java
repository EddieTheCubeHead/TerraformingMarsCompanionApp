package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class OpenCity extends Card {
    public OpenCity(Game game) {
        super("greem");
        name = "Open city";
        price = 23;
        tags.add("building");
        tags.add("city");
        requirements.put("min_oxygen", 12);
        requirements.put("min_energy_production", 1);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        while (true) {
            if (owner_game.tile_handler.placeCity(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
            }
        }
        player.changeEnergyProduction(-1);
        player.changeMoneyProduction(4);
        player.changePlants(2);
        super.onPlay(player);
    }
}
