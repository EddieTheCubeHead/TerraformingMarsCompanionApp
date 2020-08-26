package com.example.terraformingmarscompanionapp.exceptions;

import android.content.Context;

public class ActionUsedException extends GameplayException {
    public ActionUsedException() {
        super("Action already used this generation");
    }

    @Override
    public void resolve(Context context) {

    }
}
