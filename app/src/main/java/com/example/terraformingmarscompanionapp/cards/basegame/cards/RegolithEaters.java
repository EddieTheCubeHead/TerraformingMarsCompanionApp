package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.util.Log;

import com.example.terraformingmarscompanionapp.cardSubclasses.MetadataAction;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;

public final class RegolithEaters extends ResourceCard implements MetadataAction {
    public RegolithEaters(Game game) {
        super(Type.BLUE, game);
        name = "Regolith eaters";
        price = 13;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.MICROBE);
        resource_type = ResourceType.MICROBE;
    }

    @Override
    public Integer cardAction() {
        if (action_used) {
            return -1;
        } else {
            boolean added_microbes = true;
            //TODO UI kysymään lisääkö mikrobeita vai nostaako happea
            if (added_microbes) {
                resource_amount++;
            } else if (resource_amount <= 2) {
                resource_amount -= 2;
                owner_game.raiseOxygen(owner_player);
            } else {
                return -1;
            }
            action_used = true;
            return added_microbes ? 0 : 1;
        }
    }

    @Override
    public boolean actionWithMetadata(Integer data) {
        if (action_used) {
            return false;
        } else {
            if (data == 0) {
                resource_amount++;
            } else if (resource_amount <= 2) {
                resource_amount -= 2;
                owner_game.raiseOxygen(owner_player);
            } else {
                Log.i("Card", "Error in regolith eater checks!");
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
