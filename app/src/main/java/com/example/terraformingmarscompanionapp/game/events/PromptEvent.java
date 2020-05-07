package com.example.terraformingmarscompanionapp.game.events;

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
    public void playEvent() {
        GameController.getInstance().promptUser(text);
    }
}
