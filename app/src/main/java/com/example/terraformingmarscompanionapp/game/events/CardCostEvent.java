package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.ui.playDialogues.CardCostDialog;
import com.example.terraformingmarscompanionapp.webSocket.packets.CardCostPacket;

/**
 * Use a ghost card to get a card cost packet via the resource dialog UI. Used with some actions.
 */

public final class CardCostEvent extends GameEvent {
    private CardCostPacket packet;

    private Card card;

    public CardCostEvent(Card card) {
        this.card = card;
    }

    @Override
    public void playEvent(Context context) {
        Log.i("Event played", toString());
        CardCostDialog.displayDialog(context, card, true);
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("Card cost event: card: %s", card.getName());
    }
}
