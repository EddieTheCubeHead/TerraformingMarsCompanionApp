package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.CardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class NitriteReducingBacteria extends ResourceCard implements ActionCard {
    public NitriteReducingBacteria(Game game) {
        super("blue");
        name = "Nitrite reducing bacteria";
        price = 11;
        tags.add("microbe");
        resource_type = 1;
        owner_game = game;
    }

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

    public String getActionName() {
        return getName();
    }

    public Boolean getActionUsed() {
        return action_used;
    }
}
