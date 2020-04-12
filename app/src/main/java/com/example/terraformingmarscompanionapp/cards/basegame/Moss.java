package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Moss extends Card {

    public Moss(Game game) {
        super("green");
        name = "Moss";
        price = 4;
        tags.add("plant");
        requirements.put("min_oceans", 3);
        requirements.put("min_plants", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlants(-1);
        player.changePlantsProduction(1);
        super.onPlay(player);
    }
}
