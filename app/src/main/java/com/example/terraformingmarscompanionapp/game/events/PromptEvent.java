package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.terraformingmarscompanionapp.game.GameController;

/**
 * A simple implementation of {@link GameEvent} to display a customisable prompt to the player as one
 * event.
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public class PromptEvent implements GameEvent {
    private String text;

    /**
     * Constructor
     *
     * @param text {@link String} the text of the prompt
     */
    public PromptEvent(String text) {
        this.text = text;
    }

    @Override
    public void playEvent(Context context) {
        Log.i("Event played", toString());
        GameController.promptUser(text, context);
    }

    /**
     * toString overridden for logging and debugging
     *
     * @return {@link String} a verbal representation of the event
     */
    @NonNull
    @Override
    public String toString() {
        return String.format("Prompt event: message: %s", text);
    }
}
