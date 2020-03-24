package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Farming extends Card {
    public Farming(Game game) {
        name = "Farming";
        price = 16;
        tags.put("plant", 1);
        requirements.put("min_temperature", 4);
        victory_points = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addPlantTag();
        player.changePlants(2);
        player.changePlantsProduction(2);
        player.changeMoneyProduction(2);
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
