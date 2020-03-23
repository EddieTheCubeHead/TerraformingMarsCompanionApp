package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Bushes extends Card {
    public Bushes(Game game) {
        name = "Bushes";
        price = 10;
        tags.put("plant", 1);
        requirements.put("min_temperature", 10);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addPlantTag();
        player.changePlantsProduction(2);
        player.changePlants(2);
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
