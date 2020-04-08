package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class TowingAComet extends Card {
    public TowingAComet(Game game) {
        super("red");
        name = "Towing a comet";
        price = 23;
        tags.add("space");
        tags.add("event");
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
        player.changePlants(2);
        owner_game.raiseOxygen(player);
        super.onPlay(player);
    }
}
