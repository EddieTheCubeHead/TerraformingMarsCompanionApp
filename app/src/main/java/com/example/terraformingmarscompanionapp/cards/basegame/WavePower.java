package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class WavePower extends Card {
    public WavePower(Game game) {
        name = "Wave power";
        price = 8;
        tags.put("energy", 1);
        requirements.put("min_oceans", 3);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addEnergyTag();
        player.changeEnergyProduction(1);
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
