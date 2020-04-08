package com.example.terraformingmarscompanionapp.cards.colonies;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class SolarReflectors extends Card {
    public SolarReflectors(Game game) {
        super("green");
        name = "Solar reflectors";
        price = 23;
        tags.add("space");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeHeatProduction(5);
        super.onPlay(player);
    }
}
