package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Comet extends Card {
    public Comet(Game game) {
        super("red");
        name = "Comet";
        price = 21;
        tags.add("event");
        tags.add("space");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeOcean(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
            }
        }
        owner_game.raiseTemperature(player);
        //TODO poista toiselta kolme kasvia UI

        super.onPlay(player);
    }
}
