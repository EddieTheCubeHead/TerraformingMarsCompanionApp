package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class CloudSeeding extends Card {
    public CloudSeeding(Game game) {
        super(Type.GREEN);
        name = "Cloud seeding";
        price = 11;
        requirements.setMinOceans(3);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeMoneyProduction(-1);
        //TODO poista toiselta pelaajalta lämmöntuotanto
        player.changePlantsProduction(2);
        return super.onPlay(player);
    }
}
