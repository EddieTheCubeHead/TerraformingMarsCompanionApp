package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.Game;

/**
 * Simple and perhaps a bit unneeded class for "cardlike actions" that need to be represented in the UI
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 27/05/2020
 */
public abstract class CardlikeOperation extends Card {

    /**
     * Custom constuctor ease the creation of the utility cards.
     *
     * @param game {@link Game} that the card is associated with
     */
    public CardlikeOperation(Game game) {
        super(Type.STANDARD_PROJECT, game);
        price = 0;
    }
}
