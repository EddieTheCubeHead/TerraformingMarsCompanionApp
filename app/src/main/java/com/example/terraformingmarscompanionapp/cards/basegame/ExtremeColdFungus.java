package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class ExtremeColdFungus extends Card implements ActionCard {
    public ExtremeColdFungus(Game game) {
        super("blue");
        name = "Extreme-cold fungus";
        price = 13;
        tags.add("microbe");
        requirements.put("max_temperature", -10);
        owner_game = game;
    }

    @Override
    public boolean cardAction() {
        if (action_used) {
            return false;
        } else {
            Boolean chose_microbes = true;
            //TODO UI kasveja vai mikrobeja UI
            if (chose_microbes) {
                //TODO lisää toiselle kortilla kaksi mikrobia
            } else {
                owner_player.changePlants(1);
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
