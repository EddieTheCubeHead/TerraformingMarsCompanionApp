package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;

public final class OreProcessor extends Card implements ActionCard {
    public OreProcessor(Game game) {
        super("blue");
        name = "Ore processor";
        price = 13;
        tags.add("building");
        owner_game = game;
    }

    @Override
    public boolean cardAction() {
        if (action_used | owner_player.getEnergy() < 4) {
            return false;
        } else {
            owner_player.changeEnergy(-4);
            owner_player.changeTitanium(1);
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
