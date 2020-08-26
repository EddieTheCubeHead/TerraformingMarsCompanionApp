package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.util.Log;

import com.example.terraformingmarscompanionapp.game.cardClasses.ActionCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;

import java.util.ArrayList;
import java.util.Arrays;

public final class GHGProducingBacteria extends ResourceCard implements ActionCard {
    public GHGProducingBacteria() {
        super(Type.BLUE);
        name = "GHG producing bacteria";
        price = 8;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.MICROBE);
        requirements.setMinOxygen(4);
        resource_type = ResourceType.MICROBE;
    }

    @Override
    public void cardAction() {
        if (resource_amount < 2) {
            actionServerHook(owner_player, 0);
            return;
        }
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new MetadataChoiceEvent("Choose what to do.",
                new ArrayList<>(Arrays.asList("Add a microbe", "Raise temperature")),
                this, ChoiceDialog.UseCase.GENERAL));
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void actionWithMetadata(Integer data) {
        if (data == 0) {
            resource_amount++;
        } else if (resource_amount < 2) {
            Log.i("Card", "Error in GHG producing bacteria checks!");
        } else {
            resource_amount -= 2;
            game.raiseTemperature(owner_player);
        }
        EventScheduler.playNextEvent(GameController.getContext());
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
