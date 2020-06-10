package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;

import java.util.ArrayList;
import java.util.Arrays;

public final class BusinessNetwork extends Card implements ActionCard {
    public BusinessNetwork(Game game) {
        super(Type.BLUE, game);
        name = "Business network";
        price = 4;
        tags.add(Tag.EARTH);
        owner_game = game;
        requirements.setMinMoneyProduction(-4);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setMoneyProduction(-1);
        super.playWithMetadata(player, data);
    }

    @Override
    public void cardAction() {
        EventScheduler.addEvent(new ActionUseEvent());
        if (owner_player.getResources().getMoney() >= owner_player.getModifiers().getCardResearchCostModifier() + 3) {
            EventScheduler.addEvent(new MetadataChoiceEvent("Did you buy the card?", new ArrayList<>(Arrays.asList("Yes", "No")), this, ChoiceDialog.USE_CASE.GENERAL));
        } else {
            EventScheduler.addEvent(new PlayCardEvent(this, owner_player, 0));
            EventScheduler.addEvent(new PromptEvent("Not enough money to buy the card. Action used to look at a card."));
        }
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void actionWithMetadata(Integer data) {
        if (data == 0) {
            return;
        }
        owner_player.getResources().setMoney(owner_player.getResources().getMoney() - (3 + owner_player.getModifiers().getCardResearchCostModifier()));
        owner_player.changeHandSize(1);
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionValidity() {
        return (action_used);
    }

    @Override
    public void setActionToUsed() {

    }
}
