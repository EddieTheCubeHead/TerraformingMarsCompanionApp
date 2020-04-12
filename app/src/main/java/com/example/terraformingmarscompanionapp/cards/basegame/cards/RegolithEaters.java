package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.Game;

public final class RegolithEaters extends ResourceCard implements ActionCard {
    public RegolithEaters(Game game) {
        super("blue");
        name = "Regolith eaters";
        price = 13;
        tags.add("science");
        tags.add("microbe");
        resource_type = 1;
        owner_game = game;
    }

    @Override
    public boolean cardAction() {
        if (action_used) {
            return false;
        } else {
            boolean added_microbes = true;
            //TODO UI kysymään lisääkö mikrobeita vai nostaako happea
            if (added_microbes) {
                resource_amount++;
            } else if (resource_amount <= 2) {
                resource_amount -= 2;
                owner_game.raiseOxygen(owner_player);
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
