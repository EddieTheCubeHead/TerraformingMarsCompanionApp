package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class CloudSeeding extends Card {
    public CloudSeeding(Game game) {
        super("green");
        name = "Cloud seeding";
        price = 11;
        requirements.put("min_oceans", 3);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeMoneyProduction(-1);
        //TODO poista toiselta pelaajalta lämmöntuotanto
        player.changePlantsProduction(2);
        super.onPlay(player);
    }
}
