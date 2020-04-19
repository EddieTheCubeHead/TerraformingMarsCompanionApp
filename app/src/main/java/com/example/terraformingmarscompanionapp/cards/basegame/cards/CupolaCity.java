package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class CupolaCity extends Card {
    public CupolaCity(Game game) {
        super(Type.GREEN);
        name = "Cupola city";
        price = 16;
        tags.add(Tag.CITY);
        tags.add(Tag.BUILDING);
        requirements.setMaxOxygen(9);
        requirements.setMinEnergyProduction(1);
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
        player.changeEnergyProduction(-1);
        player.changeMoneyProduction(3);
        player.addCity();
        return super.onPlay(player);
    }
}
