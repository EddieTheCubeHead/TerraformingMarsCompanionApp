package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class UndergroundCity extends Card {
    public UndergroundCity(Game game) {
        super(Type.GREEN);
        name = "Underground city";
        price = 18;
        tags.add(Tag.CITY);
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyProduction(2);
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
        player.changeEnergyProduction(-2);
        player.changeSteelProduction(2);
        player.addCity();
        super.onPlay(player);
    }
}
