package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.ui.main.BooleanDialogActivity;
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;

import java.util.ArrayList;
import java.util.Arrays;

public final class ElectroCatapult extends Card implements ActionCard {
    public ElectroCatapult(Game game) {
        super(Type.BLUE, game);
        name = "Electro catapult";
        price = 17;
        tags.add(Tag.BUILDING);
        requirements.setMaxOxygen(8);
        requirements.setMinEnergyProduction(1);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setEnergyProduction(-1);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    public void cardAction() {
        EventScheduler.addEvent(new ActionUseEvent());
        if (owner_player.getSteel() < 1) {
            EventScheduler.addEvent(new PlayCardEvent(this, owner_player, 0));
        } else if (owner_player.getPlants() < 1) {
            EventScheduler.addEvent(new PlayCardEvent(this, owner_player, 1));
        } else {
            EventScheduler.addEvent(new MetadataChoiceEvent("Choose which resource to spend:",
                    new ArrayList<>(Arrays.asList("Plants (x1)", "Steel (x1)")), this, ChoiceDialog.USE_CASE.GENERAL));
        }
        EventScheduler.playNextEvent(GameController.getContext());
    }


    @Override
    public void actionWithMetadata(Integer data) {
        if (data == 0) {
            owner_player.takePlants(1);
        } else {
            owner_player.takeSteel(1);
        }
        owner_player.changeMoney(7);
        EventScheduler.playNextEvent(GameController.getContext());
    }

    @Override
    public void setActionToUsed() {
        action_used = true;
    }

    public String getActionName() {
        return getName();
    }

    public Boolean getActionValidity() {
        return (action_used || (owner_player.getSteel() < 1 && owner_player.getPlants() < 1));
    }
}
