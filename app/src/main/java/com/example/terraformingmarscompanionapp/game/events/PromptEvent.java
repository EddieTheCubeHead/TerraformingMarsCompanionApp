package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.terraformingmarscompanionapp.game.GameController;

/**
 * Simple prompt with some declareable text
 */
public class PromptEvent extends GameEvent {
    private String text;

    public PromptEvent(String text) {
        this.text = text;
    }

    @Override
    public void playEvent(Context context) {
        Log.i("Event played", toString());
        GameController.promptUser(text, context);
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("Prompt event: message: %s", text);
    }
}
