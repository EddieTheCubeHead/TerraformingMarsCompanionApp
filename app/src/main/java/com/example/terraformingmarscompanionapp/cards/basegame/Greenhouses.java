package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Greenhouses extends Card {
    public Greenhouses(Game game) {
        name = "Greenhouses";
        price = 6;
        tags.put("building", 1);
        tags.put("plant", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlants(owner_game.getCitiesInSpace() + owner_game.getCitiesOnMars());
        player.addGreen(this);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
