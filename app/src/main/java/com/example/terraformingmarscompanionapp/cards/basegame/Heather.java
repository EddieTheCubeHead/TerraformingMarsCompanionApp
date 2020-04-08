package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Heather extends Card {
    public Heather(Game game) {
        super("green");
        name = "Heather";
        price = 6;
        tags.add("plant");
        requirements.put("min_temperature", -14);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlants(1);
        player.changePlantsProduction(1);
        super.onPlay(player);
    }
}
