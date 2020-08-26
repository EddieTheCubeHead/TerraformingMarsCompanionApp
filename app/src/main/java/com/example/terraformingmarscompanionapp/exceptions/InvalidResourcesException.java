package com.example.terraformingmarscompanionapp.exceptions;

import com.example.terraformingmarscompanionapp.game.GameResourceType;

import java.util.ArrayList;
import java.util.Arrays;

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
}
