package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.ui.playDialogues.IntegerDialog;

public final class MetadataIntegerEvent extends GameEvent {
    private String message;
    private Integer min;
    private Integer max;
    private Card card;

    public MetadataIntegerEvent(String message, Integer min, Integer max, Card card) {
        this.message = message;
        this.min = min;
        this.max = max;
        this.card = card;
    }

    @Override
    public void playEvent(Context context) {
        Log.i("Event played", toString());
        IntegerDialog.displayDialog(context, card, min, max, message);
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("Metadata integer event: card: %s", card.getName());
    }
}
