package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.ActionCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataIntegerEvent;

public final class PowerInfrastructure extends Card implements ActionCard {
    public PowerInfrastructure() {
        super(Type.BLUE);
        name = "Power infrastructure";
        price = 4;
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
    }


    @Override
    public void cardAction() {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new MetadataIntegerEvent("Give energy amount:", 0, owner_player.getResources().getEnergy(), this));
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void actionWithMetadata(Integer data) throws InvalidResourcesException {
        owner_player.getResources().setEnergy(owner_player.getResources().getEnergy() - data);
        owner_player.getResources().setMoney(owner_player.getResources().getMoney() + data);
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return action_used;
    }

    @Override
    public void setActionToUsed() {
        action_used = true;
    }
}
