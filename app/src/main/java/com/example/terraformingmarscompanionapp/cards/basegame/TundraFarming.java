package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class TundraFarming extends Card {
    public TundraFarming(Game game) {
        name = "Tundra farming";
        price = 16;
        tags.put("plant", 1);
        requirements.put("min_temperature", -6);
        victory_points = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addPlantTag();
        player.changePlants(1);
        player.changePlantsProduction(1);
        player.changeMoneyProduction(2);
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
