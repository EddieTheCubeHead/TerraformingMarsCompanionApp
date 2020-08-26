package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.ActionCard;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;

import java.util.ArrayList;
import java.util.Arrays;

public final class ElectroCatapult extends Card implements ActionCard {
    public ElectroCatapult() {
        super(Type.BLUE);
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
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }

    public void cardAction() {
        EventScheduler.addEvent(new ActionUseEvent());
        if (owner_player.getResources().getSteel() < 1) {
            EventScheduler.addEvent(new PlayCardEvent(this, owner_player, 0));
        } else if (owner_player.getResources().getPlants() < 1) {
            EventScheduler.addEvent(new PlayCardEvent(this, owner_player, 1));
        } else {
            EventScheduler.addEvent(new MetadataChoiceEvent("Choose which resource to spend:",
                    new ArrayList<>(Arrays.asList("Plants (x1)", "Steel (x1)")), this, ChoiceDialog.UseCase.GENERAL));
        }
        EventScheduler.playNextEvent(GameController.getContext());
    }


    @Override
    public void actionWithMetadata(Integer data) {
        if (data == 0) {
            owner_player.getResources().setPlants(owner_player.getResources().getPlants() - 1);
        } else {
            owner_player.getResources().setSteel(owner_player.getResources().getSteel() - 1);
        }
        owner_player.getResources().setMoney(owner_player.getResources().getMoney() + 7);
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
        return (action_used || (owner_player.getResources().getSteel() < 1 && owner_player.getResources().getPlants() < 1));
    }
}
