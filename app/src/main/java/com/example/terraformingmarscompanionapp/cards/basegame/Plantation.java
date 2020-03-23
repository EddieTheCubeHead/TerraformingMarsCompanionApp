package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Plantation extends Card {
    public Plantation(Game game) {
        name = "Plantation";
        price = 15;
        tags.put("plant", 1);
        requirements.put("min_science_tags", 2);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addPlantTag();
        player.addGreen(this);
        owner_game.placeForest(player, false);
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
