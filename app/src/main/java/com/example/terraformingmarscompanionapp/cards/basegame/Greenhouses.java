package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Greenhouses extends Card {
    public Greenhouses(Game game) {
        super("green");
        name = "Greenhouses";
        price = 6;
        tags.add("building");
        tags.add("plant");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlants(owner_game.getCitiesInSpace() + owner_game.getCitiesOnMars());
        super.onPlay(player);
    }
}
