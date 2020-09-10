package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.ActionCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.ui.gameMainElements.dialogues.ChoiceDialog;

import java.util.ArrayList;
import java.util.Arrays;

public final class SearchForLife extends ResourceCard implements ActionCard {
    public SearchForLife() {
        super(Type.BLUE);
        name = "Search for life";
        price = 3;
        tags.add(Tag.SCIENCE);
        requirements.setMaxOxygen(6);
        resource_type = ResourceType.SCIENCE;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardAction() {
        //TODO another UI for simple stuff like this
        EventScheduler.addEvent(new MetadataChoiceEvent("Did you draw a card with a microbe tag?",
                new ArrayList<>(Arrays.asList("No", "Yes")), this, ChoiceDialog.UseCase.GENERAL));
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void actionWithMetadata(Integer data) throws InvalidResourcesException {
        owner_player.getResources().setMoney(owner_player.getResources().getMoney() - 1);
        if (data >= 1) {
            resource_amount++;
        }
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void setActionToUsed() {
        action_used = true;
    }

    @Override
    public void onGameEnd() {
        if (resource_amount > 0) {
            victory_points = 3;
        }
        super.onGameEnd();
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return (action_used || (owner_player.getResources().getMoney() < 1));
    }
}
