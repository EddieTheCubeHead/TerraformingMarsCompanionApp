package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.ui.main.BooleanDialogActivity;

public final class GHGProducingBacteria extends ResourceCard implements ActionCard {
    public GHGProducingBacteria(Game game) {
        super(Type.BLUE, game);
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
        Context context = GameController.getContext();
        Intent intent = new Intent(context, BooleanDialogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(BooleanDialogActivity.CARD_NAME, this.getName());
        intent.putExtra(BooleanDialogActivity.TITLE_TEXT, "Add microbe or raise temperature?");
        intent.putExtra(BooleanDialogActivity.FALSE_TEXT, "Microbe");
        intent.putExtra(BooleanDialogActivity.TRUE_TEXT, "Temperature");
        context.startActivity(intent);
    }

    @Override
    public void actionWithMetadata(Integer data) {
        if (data == 0) {
            resource_amount++;
        } else if (resource_amount < 2) {
            Log.i("Card", "Error in GHG producing bacteria checks!");
        } else {
            resource_amount -= 2;
            owner_game.raiseTemperature(owner_player);
        }
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
