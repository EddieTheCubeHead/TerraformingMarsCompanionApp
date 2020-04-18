package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;

public final class SpaceMirrors extends Card implements ActionCard {
    public SpaceMirrors(Game game) {
        super(Type.BLUE);
        name = "Space mirrors";
        price = 3;
        tags.add(Tag.ENERGY);
        tags.add(Tag.SPACE);
        owner_game = game;
    }

    @Override
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

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionUsed() {
        return action_used;
    }
}
