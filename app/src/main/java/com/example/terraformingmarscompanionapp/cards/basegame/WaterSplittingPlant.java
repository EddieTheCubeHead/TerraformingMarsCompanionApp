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
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction(Player player) {
        if (player.getEnergy() < 3) {
            return false;
        } else {
            player.changeEnergy(-3);
            owner_game.raiseOxygen(player);
            return true;
        }
    }
}
