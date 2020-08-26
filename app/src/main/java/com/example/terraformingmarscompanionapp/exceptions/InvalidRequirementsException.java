package com.example.terraformingmarscompanionapp.exceptions;

import android.content.Context;

public class InvalidRequirementsException extends GameplayException {
    public InvalidRequirementsException(String error_message) {
        super(error_message);
    }

    @Override
    public void resolve(Context context) {

    }
}
