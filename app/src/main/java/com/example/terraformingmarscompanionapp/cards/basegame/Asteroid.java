package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Asteroid extends Card {
    public Asteroid(Game game) {
        super("event");
        name = "Asteroid";
        price = 14;
        tags.add("space");
        tags.add("event");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.raiseTemperature(player);
        player.changeTitanium(2);
        //TODO poista toiselta 3 kasvia
        super.onPlay(player);
    }
}
