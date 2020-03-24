package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Ironworks extends Card {
    public Ironworks(Game game) {
        name = "Ironworks";
        price = 11;
        tags.put("building", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addBuildingTag();
        player.addAction(this);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        if (action_used | owner_player.getEnergy() < 4) {
            return false;
        } else {
            owner_player.changeEnergy(-4);
            owner_player.changeSteel(1);
            owner_game.raiseOxygen(owner_player);
            action_used = true;
            return true;
        }
    }
}
