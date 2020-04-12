package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class BigAsteroid extends Card {
    public BigAsteroid(Game game) {
        super("red");
        name = "Big asteroid";
        price = 27;
        tags.add("space");
        tags.add("event");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.raiseTemperature(player);
        owner_game.raiseTemperature(player);
        player.changeTitanium(4);
        //TODO poista 4 kasvia muilta
        super.onPlay(player);
    }
}
