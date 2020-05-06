package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;

public final class InventorsGuild extends Card implements ActionCard {
    public InventorsGuild(Game game) {
        super(Type.GREEN, game);
        name = "Inventors' guild";
        price = 9;
        tags.add(Tag.SCIENCE);
    }

    @Override
    public Integer getPrice() {
        return super.getPrice();
    }

    @Override
    public void cardAction() {
        //TODO boolean valinta UI
    }

    @Override
    public void actionWithMetadata(Integer data) {
        if (data != 0) {
            owner_player.takeMoney(3);
            owner_player.changeHandSize(1);
        }
    }

    @Override
    public void setActionToUsed() {
        action_used = true;
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return action_used;
    }
}

