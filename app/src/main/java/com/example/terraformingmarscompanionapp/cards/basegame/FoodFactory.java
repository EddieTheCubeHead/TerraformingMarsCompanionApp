package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class FoodFactory extends Card {
    public FoodFactory(Game game) {
        name = "Food factory";
        price = 12;
        tags.put("buildilng", 1);
        requirements.put("min_plant_production", 1);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addBuildingTag();
        player.changePlants(-1);
        player.changeMoneyProduction(4);
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
