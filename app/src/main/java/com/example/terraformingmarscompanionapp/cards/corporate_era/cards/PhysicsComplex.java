package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;

public final class PhysicsComplex extends ResourceCard implements ActionCard {
    public PhysicsComplex(Game game) {
        super(Type.BLUE);
        name = "Physics complex";
        price = 12;
        tags.add(Tag.BUILDING);
        tags.add(Tag.SCIENCE);
    }

    @Override
    public Integer cardAction() {
        if (action_used | owner_player.getEnergy() < 6) {
            return -1;
        } else {
            owner_player.changeEnergy(-6);
            resource_amount++;
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

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount * 2);
    }
}
