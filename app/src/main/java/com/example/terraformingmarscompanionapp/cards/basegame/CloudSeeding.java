package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

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
