package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.util.Log;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;

public final class RegolithEaters extends ResourceCard implements ActionCard {
    public RegolithEaters(Game game) {
        super(Type.BLUE, game);
        name = "Regolith eaters";
        price = 13;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.MICROBE);
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
        } else if (resource_amount <= 2) {
            resource_amount -= 2;
            owner_game.raiseOxygen(owner_player);
        } else {
            Log.i("Card", "Error in regolith eater checks!");
        }
        GameController.getInstance().useAction();
    }

    @Override
    public void setActionToUsed() {
        action_used = true;
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return action_used;
    }
}
