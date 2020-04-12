package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Algae extends Card {
    public Algae(Game game) {
        super("green");
        name = "Algae";
        price = 10;
        tags.add("plant");
        requirements.put("min_oceans", 5);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlantsProduction(2);
        player.changePlants(1);
        super.onPlay(player);
    }
}
