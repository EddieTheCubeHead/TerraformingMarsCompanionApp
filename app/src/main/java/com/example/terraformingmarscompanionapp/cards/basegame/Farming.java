package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Farming extends Card {
    public Farming(Game game) {
        super("green");
        name = "Farming";
        price = 16;
        tags.add("plant");
        requirements.put("min_temperature", 4);
        victory_points = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlants(2);
        player.changePlantsProduction(2);
        player.changeMoneyProduction(2);
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
