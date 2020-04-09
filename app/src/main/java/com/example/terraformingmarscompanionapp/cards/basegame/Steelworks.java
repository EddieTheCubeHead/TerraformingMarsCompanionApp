package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Steelworks extends Card implements ActionCard {
    public Steelworks(Game game) {
        super("blue");
        name = "Steelworks";
        price = 15;
        tags.add("building");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addBuildingTag();
        player.addAction(this);
        owner_player = player;
    }

    public boolean cardAction() {
        if (action_used | owner_player.getEnergy() > 4) {
            return false;
        } else {
            owner_player.changeSteel(2);
            owner_player.changeEnergy(-4);
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
