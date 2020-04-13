package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Capital extends Card {
    public Capital(Game game) {
        super("green");
        name = "Capital";
        price = 26;
        tags.add("building");
        tags.add("city");
        requirements.setMinOceans(4);
        requirements.setMinEnergyProduction(2);
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
        player.addCity();
        super.onPlay(player);
    }
}
