package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;

public final class Ironworks extends Card implements ActionCard {
    public Ironworks(Game game) {
        super(Type.BLUE);
        name = "Ironworks";
        price = 11;
        tags.add(Tag.BUILDING);
        owner_game = game;
    }

    @Override
    public boolean cardAction() {
        if (action_used | owner_player.getEnergy() < 4) {
            return false;
        } else {
            owner_player.changeEnergy(-4);
            owner_player.changeSteel(1);
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
