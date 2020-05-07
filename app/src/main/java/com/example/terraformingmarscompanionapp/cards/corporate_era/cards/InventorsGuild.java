package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.ui.main.BooleanDialogActivity;

public final class InventorsGuild extends Card implements ActionCard {
    public InventorsGuild(Game game) {
        super(Type.GREEN, game);
        name = "Inventors' guild";
        price = 9;
        tags.add(Tag.SCIENCE);
    }

    @Override
    public Integer getPrice() {
        return super.getPrice();
    }

    @Override
    public void cardAction() {
        Context context = GameController.getInstance().getContext();
        Intent intent = new Intent(context, BooleanDialogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(BooleanDialogActivity.CARD_NAME, this.getName());
        intent.putExtra(BooleanDialogActivity.TITLE_TEXT, "Did you guys the card?");
        intent.putExtra(BooleanDialogActivity.FALSE_TEXT, "No");
        intent.putExtra(BooleanDialogActivity.TRUE_TEXT, "Yes");
        context.startActivity(intent);
    }

    @Override
    public void actionWithMetadata(Integer data) {
        if (data != 0) {
            owner_player.takeMoney(3);
            owner_player.changeHandSize(1);
        }
        GameController.getInstance().useAction();
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

