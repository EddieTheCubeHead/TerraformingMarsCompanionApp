package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Bushes extends Card {
    public Bushes(Game game) {
        super("green");
        name = "Bushes";
        price = 10;
        tags.add("plant");
        requirements.put("min_temperature", 10);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlantsProduction(2);
        player.changePlants(2);
        super.onPlay(player);
    }
}
