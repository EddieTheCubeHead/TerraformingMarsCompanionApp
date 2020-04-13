package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;

public final class ExtremeColdFungus extends Card implements ActionCard {
    public ExtremeColdFungus(Game game) {
        super("blue");
        name = "Extreme-cold fungus";
        price = 13;
        tags.add("microbe");
        requirements.setMaxTemperature(-10);
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
