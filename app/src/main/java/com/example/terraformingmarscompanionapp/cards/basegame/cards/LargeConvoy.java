package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class LargeConvoy extends Card {
    public LargeConvoy(Game game) {
        super("red");
        name = "Large convoy";
        price = 36;
        tags.add("space");
        tags.add("earth");
        tags.add("event");
        victory_points = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        boolean chose_plants = true;
        //TODO kasveja 5 tai eläimiä 4 UI
        if (chose_plants) {
            player.changePlants(5);
        } else {
            //TODO lisää 4 eläintä toiselle kortille
        }
        //TODO prompti nostaa kaksi korttia
        super.onPlay(player);
    }
}
