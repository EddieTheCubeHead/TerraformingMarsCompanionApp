package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class SpaceMirrors extends Card implements ActionCard {
    public SpaceMirrors(Game game) {
        super("blue");
        name = "Space mirrors";
        price = 3;
        tags.add("energy");
        tags.add("space");
        owner_game = game;
    }

    public boolean cardAction() {
        if ((owner_player.getMoney() < 7) | action_used) {
            return false;
        } else {
            owner_player.changeMoney(-7);
            owner_player.changeEnergyProduction(1);
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
