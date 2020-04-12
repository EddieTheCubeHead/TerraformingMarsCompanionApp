package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

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

    @Override
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

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionUsed() {
        return action_used;
    }
}
