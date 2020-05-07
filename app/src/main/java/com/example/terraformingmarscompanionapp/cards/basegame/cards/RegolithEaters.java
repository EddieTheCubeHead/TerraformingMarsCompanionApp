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

public final class RegolithEaters extends ResourceCard implements ActionCard {
    public RegolithEaters(Game game) {
        super(Type.BLUE, game);
        name = "Regolith eaters";
        price = 13;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.MICROBE);
        resource_type = ResourceType.MICROBE;
    }

    @Override
    public void cardAction() {
        if (resource_amount < 2) {
            GameController.getInstance().useAction();
            onPlayServerHook(owner_player, 0);
            return;
        }
        Context context = GameController.getInstance().getContext();
        Intent intent = new Intent(context, BooleanDialogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(BooleanDialogActivity.CARD_NAME, this.getName());
        intent.putExtra(BooleanDialogActivity.TITLE_TEXT, "Add a microbe or raise oxygen?");
        intent.putExtra(BooleanDialogActivity.FALSE_TEXT, "Microbe");
        intent.putExtra(BooleanDialogActivity.TRUE_TEXT, "Oxygen");
        context.startActivity(intent);
    }

    @Override
    public void actionWithMetadata(Integer data) {
        if (data == 0) {
            resource_amount++;
        } else if (resource_amount <= 2) {
            resource_amount -= 2;
            owner_game.raiseOxygen(owner_player);
        } else {
            Log.i("Card", "Error in regolith eater checks!");
        }
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
