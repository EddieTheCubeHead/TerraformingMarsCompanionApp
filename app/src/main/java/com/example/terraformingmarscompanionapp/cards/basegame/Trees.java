package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Trees extends Card {
    public Trees(Game game) {
        super("green");
        name = "Trees";
        price = 13;
        tags.add("plant");
        requirements.put("min_temperature", -4);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlantsProduction(3);
        player.changePlants(1);
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
