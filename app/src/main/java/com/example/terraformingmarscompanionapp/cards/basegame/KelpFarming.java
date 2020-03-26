package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class KelpFarming extends Card {
    public KelpFarming(Game game) {
        name = "Kelp farming";
        price = 17;
        tags.put("plant", 1);
        requirements.put("min_oceans", 6);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addPlantTag();
        player.changeMoneyProduction(2);
        player.changePlantsProduction(3);
        player.changePlants(2);
        owner_game.updateManager.onVpCardPlayed(player);
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
