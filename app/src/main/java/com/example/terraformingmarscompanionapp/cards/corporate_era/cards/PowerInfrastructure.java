package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataIntegerEvent;

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
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new MetadataIntegerEvent("Give energy amount:", 0, owner_player.getEnergy(), this));
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void actionWithMetadata(Integer data) {
        owner_player.changeEnergy(-data);
        owner_player.changeMoney(data);
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return owner_player.getEnergy() != 0;
    }

    @Override
    public void setActionToUsed() {
        action_used = true;
    }
}
