package com.example.terraformingmarscompanionapp.cards.prelude.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;

public final class Psychrophiles extends ResourceCard implements ActionCard {
    public Psychrophiles(Game game) {
        super(Type.BLUE, game);
        name = "Psychrophiles";
        price = 2;
        tags.add(Tag.MICROBE);
        requirements.setMaxTemperature(-20);
        resource_type = ResourceType.MICROBE;
    }

    @Override
    public void cardAction() {
        GameController.getInstance().useAction();
        actionServerHook(owner_player);
    }

    @Override
    public void actionWithMetadata(Integer data) {
        resource_amount++;
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
