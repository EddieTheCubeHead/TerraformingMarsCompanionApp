package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class RoverConstruction extends Card {
    public RoverConstruction(Game game) {
        name = "Rover construction";
        price = 8;
        tags.put("building", 1);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addBuildingTag();
        player.addPassive(this);
        owner_game.updateManager.onVpCardPlayed(player);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player != null) {
            owner_player.changeMoney(2);
        }
    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
