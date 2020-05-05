package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;

public final class DevelopmentCenter extends Card implements ActionCard {
    public DevelopmentCenter(Game game) {
        super(Type.BLUE, game);
        name = "Development center";
        price = 11;
        tags.add(Tag.BUILDING);
        tags.add(Tag.SCIENCE);
    }

    @Override
    public Integer cardAction() {
        if (action_used || owner_player.getEnergy() == 0) {
            return -1;
        } else {
            //TODO Kortin veto
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
