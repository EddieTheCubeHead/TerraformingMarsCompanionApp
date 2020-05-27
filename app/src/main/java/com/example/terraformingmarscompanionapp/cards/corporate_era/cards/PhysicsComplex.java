package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;

public final class PhysicsComplex extends ResourceCard implements ActionCard {
    public PhysicsComplex(Game game) {
        super(Type.BLUE, game);
        name = "Physics complex";
        price = 12;
        tags.add(Tag.BUILDING);
        tags.add(Tag.SCIENCE);
    }

    @Override
    public void cardAction() {
        defaultEvents(owner_player);
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void actionWithMetadata(Integer data) {
        owner_player.changeEnergy(-6);
        resource_amount++;
        EventScheduler.playNextEvent(GameController.getContext());
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
        return (action_used || (owner_player.getEnergy() < 6));
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount * 2);
    }
}
