package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class BeamFromAThoriumAsteroid extends Card {
    public BeamFromAThoriumAsteroid(Game game) {
        name = "Beam from a thorium asteroid";
        price = 32;
        tags.put("jovian", 1);
        tags.put("space", 1);
        tags.put("energy", 1);
        requirements.put("min_jovian_tags", 1);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.updateManager.onVpCardPlayed(player);
        player.addJovianTag();
        player.addSpaceTag();
        player.addEnergyTag();
        player.changeHeatProduction(3);
        player.changeEnergyProduction(3);
        player.addGreen(this);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
