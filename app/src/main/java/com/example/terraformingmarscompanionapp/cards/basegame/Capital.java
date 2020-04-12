package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Capital extends Card {
    public Capital(Game game) {
        super("green");
        name = "Capital";
        price = 26;
        tags.add("building");
        tags.add("city");
        requirements.put("min_oceans", 4);
        requirements.put("min_energy_production", 2);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeCapital(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
            }
        }

        player.changeEnergyProduction(-2);
        player.changeMoneyProduction(5);
        owner_game.update_manager.onVpCardPlayed(player);

        super.onPlay(player);
    }
}
