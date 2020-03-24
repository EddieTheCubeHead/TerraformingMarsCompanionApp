package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class GreatDam extends Card {
    public GreatDam(Game game) {
        name = "Great dam";
        price = 12;
        tags.put("energy", 1);
        tags.put("building", 1);
        requirements.put("min_oceans", 4);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(2);
        player.addEnergyTag();
        player.addBuildingTag();
        player.addGreen(this);
        owner_player = player;
        owner_game.updateManager.onVpCardPlayed(player);
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
