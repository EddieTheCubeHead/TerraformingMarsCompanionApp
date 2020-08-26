package com.example.terraformingmarscompanionapp.game.cardClasses;

import com.example.terraformingmarscompanionapp.game.Game;

/**
 * Simple and perhaps a bit unneeded class for "cardlike actions" that need to be represented in the UI
 *
 * @author Eetu Asikainen
 * @version 0.3
 * @since 0.2
 */
public abstract class CardlikeOperation extends Card {

    /**
     * Custom constuctor ease the creation of the utility cards.
     */
    public CardlikeOperation() {
        super(Type.STANDARD_PROJECT);
        price = 0;
    }
}
