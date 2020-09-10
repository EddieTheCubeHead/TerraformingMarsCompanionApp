package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.ui.gameMainElements.dialogues.IntegerDialog;

/**
 * An implementation of {@link GameEvent} used when a card needs data in the form of a numerical
 * decision made by player. For example of usecases see {@link com.example.terraformingmarscompanionapp.cards.basegame.cards.Insulation}
 */
public final class MetadataIntegerEvent implements GameEvent {
    private String message;
    private Integer min;
    private Integer max;
    private Card card;

    /**
     * Constructor
     *
     * @param message {@link String} a message shown in the dialogue detailing the decision
     * @param min {@link Integer} the minimum value accepted. Most often 0
     * @param max {@link Integer} the maximum value accepted
     * @param card {@link Card} that the dialogue is gathering data to play
     */
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

    /**
     * toString overridden for logging and debugging
     *
     * @return {@link String} a verbal representation of the event
     */
    @NonNull
    @Override
    public String toString() {
        return String.format("Metadata integer event: card: %s", card.getName());
    }
}
