package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class Lichen extends Card {
    public Lichen(Game game) {
        super("green");
        name = "Lichen";
        price = 7;
        tags.add("plant");
        requirements.put("min_temperature", -24);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlantsProduction(1);
        super.onPlay(player);
    }
}
