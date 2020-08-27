package com.example.terraformingmarscompanionapp.exceptions;

import android.content.Context;

import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.GameResourceType;


/**
 * A custom exception for handling a situation where playing a card, using an action, or other gameplay
 * actions are unavailable due to a player lacking the required resources.
 */
public class InvalidResourcesException extends GameplayException {
    private GameResourceType resource;
    private Integer amount;

    public InvalidResourcesException(GameResourceType resource, Integer amount) {
        super("Invalid resources");

        this.resource = resource;
        this.amount = amount;
    }

    /**
     * @return {@link GameResourceType} that is lacking
     */
    public GameResourceType getResource() {
        return resource;
    }

    /**
     * @return {@link Integer} the amount of resource lacking
     */
    public Integer getAmount() {
        return amount;
    }

    @Override
    public void resolve(Context context) {
        String player_message = "Cannot perform action:\nNeed " + amount + " more " + resource.toString();

        if (amount != 1 && resource.requiresPlural()) {
            player_message += "s";
        }

        player_message += ".";

        defaultResolveLogic(player_message, context);
    }
}
