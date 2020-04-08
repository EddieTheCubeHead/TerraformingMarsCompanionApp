package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class OreProcessor extends Card implements ActionCard {
    public OreProcessor(Game game) {
        super("blue");
        name = "Ore processor";
        price = 13;
        tags.add("building");
        owner_game = game;
    }

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

    public String getActionName() {
        return getName();
    }

    public Boolean getActionUsed() {
        return action_used;
    }
}
