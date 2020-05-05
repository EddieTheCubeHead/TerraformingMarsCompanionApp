package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;

public final class UndergroundDetonation extends Card implements ActionCard {
    public UndergroundDetonation(Game game) {
        super(Type.BLUE, game);
        name = "Underground detonation";
        price = 6;
        tags.add(Tag.BUILDING);
    }

    @Override
    public Integer cardAction() {
        if (action_used) {
            return -1;
        } else if (owner_player != null) {
            if (owner_player.getMoney() < 10) {
                return -1;
            }
            owner_player.changeMoney(-10);
            owner_player.changeHeatProduction(2);
            action_used = true;
            return 0;
        }
        return -1;
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
