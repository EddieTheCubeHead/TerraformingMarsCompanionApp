package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Flooding extends Card {
    public Flooding(Game game) {
        super("red");
        name = "Flooding";
        price = 7;
        tags.add("event");
        victory_points = -1;
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
        //TODO poista toiselta neljä rahaa UI
        super.onPlay(player);
    }
}
