package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.ActionCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;
import com.example.terraformingmarscompanionapp.ui.gameMainElements.dialogues.ChoiceDialog;

import java.util.ArrayList;
import java.util.Arrays;

public final class InventorsGuild extends Card implements ActionCard {
    public InventorsGuild() {
        super(Type.GREEN);
        name = "Inventors' guild";
        price = 9;
        tags.add(Tag.SCIENCE);
    }

    @Override
    public void cardAction() {
        EventScheduler.addEvent(new ActionUseEvent());
        if (owner_player.getResources().getMoney() >= owner_player.getModifiers().getCardResearchCostModifier() + 3) {
            EventScheduler.addEvent(new MetadataChoiceEvent("Did you buy the card?", new ArrayList<>(Arrays.asList("Yes", "No")), this, ChoiceDialog.UseCase.GENERAL));
        } else {
            EventScheduler.addEvent(new PlayCardEvent(this, owner_player, 0));
            EventScheduler.addEvent(new PromptEvent("Not enough money to buy the card. Action used to look at a card."));
        }
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void actionWithMetadata(Integer data) throws InvalidResourcesException {
        if (data == 0) {
            return;
        }
        owner_player.getResources().setMoney(owner_player.getResources().getMoney() - (3 + owner_player.getModifiers().getCardResearchCostModifier()));
        owner_player.changeHandSize(1);
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

