package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Heather extends Card {
    public Heather(Game game) {
        super("green");
        name = "Heather";
        price = 6;
        tags.add("plant");
        requirements.setMinTemperature(-14);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlants(1);
        player.changePlantsProduction(1);
        super.onPlay(player);
    }
}
