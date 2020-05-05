package com.example.terraformingmarscompanionapp.cards.prelude.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;

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
    public Integer cardAction() {
        if (action_used) {
            return -1;
        } else {
            resource_amount++;
            action_used = true;
            return 1;
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
