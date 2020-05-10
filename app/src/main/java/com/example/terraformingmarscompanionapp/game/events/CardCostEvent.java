package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;

import com.example.terraformingmarscompanionapp.InGameUI;
import com.example.terraformingmarscompanionapp.SearchActivity;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.ui.main.CardCostDialog;
import com.example.terraformingmarscompanionapp.webSocket.events.CardCostPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.ServerEvent;

import java.util.ArrayList;

/**
 * Use a ghost card to get a card cost packet via the resource dialog UI. Used with some actions.
 */

public final class CardCostEvent extends GameEvent implements ServerEvent {
    private CardCostPacket packet;

    private Card card;

    public CardCostEvent(Card card) {
        this.card = card;
    }

    @Override
    public void playEvent(Context context) {
        CardCostDialog.displayDialog(context, card, true);
    }

    @Override
    public void playPacket() {

    }
}
