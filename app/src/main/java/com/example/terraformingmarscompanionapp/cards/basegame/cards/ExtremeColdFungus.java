package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.ResourceEvent;
import com.example.terraformingmarscompanionapp.ui.main.BooleanDialogActivity;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.events.CardEventPacket;

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
        Context context = GameController.getInstance().getContext();
        Intent intent = new Intent(context, BooleanDialogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(BooleanDialogActivity.CARD_NAME, this.getName());
        intent.putExtra(BooleanDialogActivity.TITLE_TEXT, "Choose which resources to add:");
        intent.putExtra(BooleanDialogActivity.FALSE_TEXT, "Plants (x1)");
        intent.putExtra(BooleanDialogActivity.TRUE_TEXT, "Microbes (x2)");
        context.startActivity(intent);
    }

    @Override
    public void actionServerHook(Player player, Integer data) {
        if (GameController.getInstance().getGame().getServerMultiplayer()) {
            GameActions.sendCardEvent(new CardEventPacket(this.getActionName(), player.getName(), 0));
        }
        setActionToUsed();
        if (data == 0) {
            GameController.getInstance().addUiEvent(new ResourceEvent(ResourceCard.ResourceType.MICROBE, player, 2, true));
        }
        actionWithMetadata(data);
    }

    @Override
    public void actionWithMetadata(Integer data) {
        if (data != 0) {
            owner_player.changePlants(1);
        }
        GameController.getInstance().useAction();
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
