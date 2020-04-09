package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class EonChasmaNationalPark extends Card {
    public EonChasmaNationalPark(Game game) {
        super("green");
        name = "Eon chasma national park";
        price = 16;
        tags.add("plant");
        tags.add("building");
        requirements.put("min_temperature", -12);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlants(3);
        player.changeMoneyProduction(2);
        //TODO lisää eläin toiselle kortille
        super.onPlay(player);
    }
}
