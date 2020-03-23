package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class WaterSplittingPlant extends Card {
    public WaterSplittingPlant(Game game) {
        name = "Water splitting plant";

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
        if (owner_player.getEnergy() < 3) {
            return false;
        } else {
            owner_player.changeEnergy(-3);
            owner_game.raiseOxygen(owner_player);
            return true;
        }
    }
}
