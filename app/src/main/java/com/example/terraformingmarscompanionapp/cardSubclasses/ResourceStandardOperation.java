package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.Game;

public abstract class ResourceStandardOperation extends Card {
    public ResourceStandardOperation(Game game) {
        super(Type.STANDARD_PROJECT, game);
        price = 0;
    }
}
