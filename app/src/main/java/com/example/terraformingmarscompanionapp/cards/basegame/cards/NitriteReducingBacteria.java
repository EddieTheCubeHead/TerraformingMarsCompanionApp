package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.ActionCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;

import java.util.ArrayList;
import java.util.Arrays;

public final class NitriteReducingBacteria extends ResourceCard implements ActionCard {
    public NitriteReducingBacteria() {
        super(Type.BLUE);
        name = "Nitrite reducing bacteria";
        price = 11;
        tags.add(Tag.MICROBE);
        resource_type = ResourceType.MICROBE;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        resource_amount += 3;
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardAction() {
        if (resource_amount < 3) {
            onPlayServerHook(owner_player, 0);
            return;
        }
        EventScheduler.addEvent(new MetadataChoiceEvent("Choose what to do:",
                new ArrayList<>(Arrays.asList("Add a microbe", "Raise TR")), this, ChoiceDialog.UseCase.GENERAL));
    }

    @Override
    public void actionWithMetadata(Integer data) {
        if (data == 0) {
            resource_amount++;
        } else {
            resource_amount -= 3;
            owner_player.getResources().setTerraformingRating(owner_player.getResources().getTerraformingRating() + 1);
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
