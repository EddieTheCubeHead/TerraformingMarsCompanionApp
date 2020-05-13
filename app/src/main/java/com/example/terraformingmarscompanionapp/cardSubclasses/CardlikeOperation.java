package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.Game;

/**
 * Simple and perhaps a bit unneeded class for "cardlike actions" that need to be represented in the UI
 */

public abstract class CardlikeOperation extends Card {
    public CardlikeOperation(Game game) {
        super(Type.STANDARD_PROJECT, game);
        price = 0;
    }
}
