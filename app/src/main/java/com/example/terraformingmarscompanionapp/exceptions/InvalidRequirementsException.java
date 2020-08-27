package com.example.terraformingmarscompanionapp.exceptions;

import android.content.Context;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;

public class InvalidRequirementsException extends GameplayException {
    private String requirement_description;

    public InvalidRequirementsException(String requirement_description) {
        super();
        this.requirement_description = requirement_description;
    }

    @Override
    public void resolve(Context context) {
        String player_message = "Could not play card due to a missing requirement:\n";
        player_message += requirement_description;

        defaultResolveLogic(player_message, context);
    }
}
