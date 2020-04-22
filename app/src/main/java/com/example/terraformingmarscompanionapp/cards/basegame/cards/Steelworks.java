package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;

public final class Steelworks extends Card implements ActionCard {
    public Steelworks(Game game) {
        super(Type.BLUE);
        name = "Steelworks";
        price = 15;
        tags.add(Tag.BUILDING);
        owner_game = game;
    }

    @Override
    public Integer cardAction() {
        if (action_used | owner_player.getEnergy() > 4) {
            return -1;
        } else {
            owner_player.changeSteel(2);
            owner_player.changeEnergy(-4);
            owner_game.raiseOxygen(owner_player);
            action_used = true;
            return 0;
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
