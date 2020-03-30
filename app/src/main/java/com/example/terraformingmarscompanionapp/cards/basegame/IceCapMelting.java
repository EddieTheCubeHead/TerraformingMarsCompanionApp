package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class IceCapMelting extends Card {
    public IceCapMelting(Game game) {
        name = "Ice cap melting";
        price = 5;
        tags.put("event", 1);
        requirements.put("min_temperature", 2);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addEventTag();
        player.addRed(this);
        owner_player = player;
        while (true) {
            if (owner_game.tile_handler.placeOcean(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
            }
        }
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
