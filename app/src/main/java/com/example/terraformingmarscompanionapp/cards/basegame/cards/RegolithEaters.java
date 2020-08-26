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
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;

import java.util.ArrayList;
import java.util.Arrays;

public final class RegolithEaters extends ResourceCard implements ActionCard {
    public RegolithEaters() {
        super(Type.BLUE);
        name = "Regolith eaters";
        price = 13;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.MICROBE);
        resource_type = ResourceType.MICROBE;
    }

    @Override
    public void cardAction() {
        if (resource_amount < 2) {
            EventScheduler.addEvent(new PlayCardEvent(this, owner_player, 0));
            EventScheduler.playNextEvent(GameController.getContext());
            return;
        }
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new MetadataChoiceEvent("Choose what to do:",
                new ArrayList<>(Arrays.asList("Add a microbe", "Raise oxygen")), this, ChoiceDialog.UseCase.GENERAL));
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void actionWithMetadata(Integer data) {
        if (data == 0) {
            resource_amount++;
        } else if (resource_amount <= 2) {
            resource_amount -= 2;
            game.raiseOxygen(owner_player);
        } else {
            Log.i("Regolith eaters Error", "Invalid checks led to this being played to raise oxygen without sufficient microbes.");
        }
        EventScheduler.playNextEvent(GameController.getContext());
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
