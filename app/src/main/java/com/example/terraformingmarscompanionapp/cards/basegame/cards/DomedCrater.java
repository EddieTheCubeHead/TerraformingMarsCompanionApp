package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class DomedCrater extends Card {
    public DomedCrater(Game game) {
        super(Type.GREEN);
        name = "Domed crater";
        price = 24;
        tags.add(Tag.BUILDING);
        tags.add(Tag.CITY);
        requirements.setMaxOxygen(7);
        requirements.setMinEnergyProduction(1);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeCity(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
            }
        }

        player.changePlants(3);
        player.changeEnergyProduction(-1);
        player.changeMoneyProduction(3);
        owner_game.update_manager.onVpCardPlayed(player);
        player.addCity();
        return super.onPlay(player);
    }
}
