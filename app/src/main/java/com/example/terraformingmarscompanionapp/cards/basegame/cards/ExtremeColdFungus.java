package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.game.events.ResourceChoiceEvent;
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.packets.CardEventPacket;

import java.util.ArrayList;
import java.util.Arrays;

public final class ExtremeColdFungus extends Card implements ActionCard {
    public ExtremeColdFungus(Game game) {
        super(Type.BLUE, game);
        name = "Extreme-cold fungus";
        price = 13;
        tags.add(Tag.MICROBE);
        requirements.setMaxTemperature(-10);
    }

    @Override
    public void cardAction() {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new MetadataChoiceEvent("Choose which resources to add:", new ArrayList<>(Arrays.asList("Plants (x1)", "Microbes(x2)")), this, ChoiceDialog.USE_CASE.GENERAL));
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void actionServerHook(Player player, Integer data) {
        if (owner_game.getServerMultiplayer()) {
            GameActions.sendCardEvent(new CardEventPacket(this.getActionName(), player.getName(), data));
        }
        setActionToUsed();
        if (data == 0) {
            EventScheduler.addEvent(new ResourceChoiceEvent(ResourceCard.ResourceType.MICROBE, player, 2, true));
        }
        actionWithMetadata(data);
    }

    @Override
    public void actionWithMetadata(Integer data) {
        if (data != 0) {
            owner_player.getResources().setPlants(owner_player.getResources().getPlants() + 1);
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
