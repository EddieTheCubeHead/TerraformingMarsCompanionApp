package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class UndergroundDetonation extends Card implements ActionCard {
    public UndergroundDetonation(Game game) {
        super("blue");
        name = "Underground detonation";
        price = 6;
        tags.add("building");
        owner_game = game;
    }

    @Override
    public boolean cardAction() {
        if (action_used) {
            return false;
        } else if (owner_player != null) {
            if (owner_player.getMoney() < 10) {
                return false;
            }
            owner_player.changeMoney(-10);
            owner_player.changeHeatProduction(2);
            action_used = true;
            return true;
        }
        return false;
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
