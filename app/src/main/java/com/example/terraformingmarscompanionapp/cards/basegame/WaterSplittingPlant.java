package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class WaterSplittingPlant extends Card implements ActionCard {
    public WaterSplittingPlant(Game game) {
        super("blue");
        name = "Water splitting plant";
        price = 12;
        tags.add("building");
        requirements.put("min_oceans", 2);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addBuildingTag();
        player.addAction(this);
        owner_player = player;
    }

    @Override
    public boolean cardAction() {
        if ((owner_player.getEnergy() < 3) | action_used) {
            return false;
        } else {
            owner_player.changeEnergy(-3);
            owner_game.raiseOxygen(owner_player);
            action_used = true;
            return true;
        }
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionUsed() {
        return action_used;
    }
}
