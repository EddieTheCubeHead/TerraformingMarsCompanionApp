package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

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
