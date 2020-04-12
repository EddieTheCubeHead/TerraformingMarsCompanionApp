package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class Grass extends Card {
    public Grass(Game game) {
        super("green");
        name = "Grass";
        price = 11;
        tags.add("plant");
        requirements.put("min_temperature", -16);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlantsProduction(1);
        player.changePlants(3);
        super.onPlay(player);
    }
}
