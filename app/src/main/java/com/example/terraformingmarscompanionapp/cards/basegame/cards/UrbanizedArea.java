package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class UrbanizedArea extends Card {
    public UrbanizedArea(Game game) {
        super("green");
        name = "Urbanized area";
        price = 10;
        tags.add("city");
        tags.add("building");
        requirements.put("min_energy_production", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeUrbanizedArea(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
            }
        }
        player.changeEnergyProduction(-1);
        player.changeMoneyProduction(2);
        super.onPlay(player);
    }
}
