package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class MethaneFromTitan extends Card {
    public MethaneFromTitan(Game game) {
        name = "Methane from titan";
        price = 10;
        tags.put("jovian", 1);
        tags.put("space", 1);
        requirements.put("min_oxygen", 2);
        victory_points = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addJovianTag();
        player.addSpaceTag();
        player.changeHeatProduction(2);
        player.changePlantsProduction(2);
        player.addGreen(this);
        owner_game.updateManager.onVpCardPlayed(player);
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
