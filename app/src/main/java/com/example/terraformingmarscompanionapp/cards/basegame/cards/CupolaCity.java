package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class CupolaCity extends Card {
    public CupolaCity(Game game) {
        super("green");
        name = "Cupola city";
        price = 16;
        tags.add("city");
        tags.add("building");
        requirements.put("max_oxygen", 9);
        requirements.put("min_energy", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeCity(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
            }
        }
        player.changeEnergyProduction(-1);
        player.changeMoneyProduction(3);

        super.onPlay(player);
    }
}
