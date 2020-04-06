package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class NaturalPreserve extends Card {
    public NaturalPreserve(Game game) {
        name = "Natural preserve";
        price = 9;
        tags.put("science", 1);
        tags.put("building", 1);
        requirements.put("max_oxygen", 4);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addScienceTag();
        player.addBuildingTag();
        player.changeMoneyProduction(1);
        player.addGreen(this);
        owner_player = player;
        owner_game.updateManager.onVpCardPlayed(player);
        while (true) {
            if (owner_game.tile_handler.placeNaturalReserve(player)) {
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
