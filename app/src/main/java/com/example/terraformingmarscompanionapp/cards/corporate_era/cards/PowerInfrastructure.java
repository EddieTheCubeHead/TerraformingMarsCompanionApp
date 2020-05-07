package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;

public final class PowerInfrastructure extends Card implements ActionCard {
    public PowerInfrastructure(Game game) {
        super(Type.BLUE, game);
        name = "Power infrastructure";
        price = 4;
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
    }


    @Override
    public void cardAction() {
        actionServerHook(owner_player);
    }

    @Override
    public void actionWithMetadata(Integer data) {
        //TODO Vaihda x määrä energiaa x määräksi rahaa
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        if (owner_player.getEnergy() == 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void setActionToUsed() {
        action_used = true;
    }

    @Override
    public Boolean getActionUsed() {
        return action_used;
    }
}
