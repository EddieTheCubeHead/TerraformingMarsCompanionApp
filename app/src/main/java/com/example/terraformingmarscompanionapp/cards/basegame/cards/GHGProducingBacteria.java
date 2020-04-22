package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.util.Log;

import com.example.terraformingmarscompanionapp.cardSubclasses.MetadataAction;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;

public final class GHGProducingBacteria extends ResourceCard implements MetadataAction {
    public GHGProducingBacteria(Game game) {
        super(Type.BLUE);
        name = "GHG producing bacteria";
        price = 8;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.MICROBE);
        requirements.setMinOxygen(4);
        resource_type = ResourceType.MICROBE;
        owner_game = game;
    }

    @Override
    public Integer cardAction() {
        if (action_used) {
            return -1;
        } else {
            Boolean added_microbes = true;
            //TODO UI kysymään tämä
            if (added_microbes) {
                resource_amount++;
            } else if (resource_amount < 2) {
                return -1;
            } else {
                resource_amount -= 2;
            }
            action_used = true;
            return added_microbes ? 1 : 0;
        }
    }

    @Override
    public boolean actionWithMetadata(Integer data) {
        if (action_used) {
            return false;
        } else {
            if (data == 0) {
                resource_amount++;
            } else if (resource_amount < 2) {
                Log.i("Card", "Error in GHG producing bacteria checks!");
                return false;
            } else {
                resource_amount -= 2;
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
