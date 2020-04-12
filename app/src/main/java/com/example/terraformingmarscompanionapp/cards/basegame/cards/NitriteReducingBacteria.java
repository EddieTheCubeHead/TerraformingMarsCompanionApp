package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.Game;

public final class NitriteReducingBacteria extends ResourceCard implements ActionCard {
    public NitriteReducingBacteria(Game game) {
        super("blue");
        name = "Nitrite reducing bacteria";
        price = 11;
        tags.add("microbe");
        resource_type = 1;
        owner_game = game;
    }

    @Override
    public boolean cardAction() {
        if (action_used) {
            return false;
        } else {
            Boolean add_microbe = true;
            //TODO booleanin kysyminen UI:lla
            if (add_microbe) {
                resource_amount++;
            } else if (resource_amount < 3) {
                resource_amount -= 3;
            } else {
                return false;
            }
            action_used = true;
            return true;
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
