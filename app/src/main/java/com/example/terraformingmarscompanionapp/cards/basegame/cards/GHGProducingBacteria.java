package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.util.Log;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;

public final class GHGProducingBacteria extends ResourceCard implements ActionCard {
    public GHGProducingBacteria(Game game) {
        super(Type.BLUE, game);
        name = "GHG producing bacteria";
        price = 8;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.MICROBE);
        requirements.setMinOxygen(4);
        resource_type = ResourceType.MICROBE;
    }

    @Override
    public void cardAction() {
        //TODO boolean valinta UI
    }

    @Override
    public void actionWithMetadata(Integer data) {
        if (data == 0) {
            resource_amount++;
        } else if (resource_amount < 2) {
            Log.i("Card", "Error in GHG producing bacteria checks!");
        } else {
            resource_amount -= 2;
        }
        GameController.getInstance().useAction();
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return action_used;
    }

    @Override
    public void setActionToUsed() {
        action_used = true;
    }
}
