package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.MetadataAction;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;

public final class ExtremeColdFungus extends Card implements MetadataAction {
    public ExtremeColdFungus(Game game) {
        super(Type.BLUE);
        name = "Extreme-cold fungus";
        price = 13;
        tags.add(Tag.MICROBE);
        requirements.setMaxTemperature(-10);
        owner_game = game;
    }

    @Override
    public Integer cardAction() {
        if (action_used) {
            return -1;
        } else {
            Boolean chose_microbes = true;
            //TODO UI kasveja vai mikrobeja UI
            if (chose_microbes) {
                //TODO lisää toiselle kortilla kaksi mikrobia
            } else {
                owner_player.changePlants(1);
            }
            action_used = true;
            return chose_microbes ? 0 : 1;
        }
    }

    @Override
    public boolean actionWithMetadata(Integer data) {
        if (action_used) {
            return false;
        } else {
            if (data == 0) {
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
