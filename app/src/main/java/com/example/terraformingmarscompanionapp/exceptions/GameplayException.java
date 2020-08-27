package com.example.terraformingmarscompanionapp.exceptions;

import android.content.Context;

import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;

public abstract class GameplayException extends Exception {
    public GameplayException(String error_message) {
        super(error_message);
    }

    public GameplayException() {
        super();
    }

    public abstract void resolve(Context context);

    protected void defaultResolveLogic(String player_message, Context context) {
        EventScheduler.clear();

        try {
            GameController.loadGame();
        } catch (Exception e) {
            e.printStackTrace();
        }

        GameController.promptUser(player_message, context);
    }
}
