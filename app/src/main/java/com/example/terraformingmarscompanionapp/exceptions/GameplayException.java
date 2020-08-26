package com.example.terraformingmarscompanionapp.exceptions;

import android.content.Context;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;

public abstract class GameplayException extends Exception {
    public GameplayException(String error_message) {
        super(error_message);
    }

    public GameplayException() {
        super();
    }

    public abstract void resolve(Context context);
}
