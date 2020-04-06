package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class WaterImportFromEurope extends Card {
    public WaterImportFromEurope(Game game) {
        name = "Water import from europe";
        price = 25;
        tags.put("space", 1);
        tags.put("jovian", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {

    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        if (action_used) {
            return false;
        } else {
            //TODO implementoi ota 12 rahana/titaanina
            action_used = true;
            while (true) {
                if (owner_game.tile_handler.placeOcean(owner_player)) {
                    break;
                } else {
                    //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
                }
            }
            return true;
        }
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(owner_player.getJovianTags());
    }
}
