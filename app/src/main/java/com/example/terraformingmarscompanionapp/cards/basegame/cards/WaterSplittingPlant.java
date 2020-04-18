package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class WaterSplittingPlant extends Card implements ActionCard {
    public WaterSplittingPlant(Game game) {
        super(Type.BLUE);
        name = "Water splitting plant";
        price = 12;
        tags.add(Tag.BUILDING);
        requirements.setMinOceans(2);
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
