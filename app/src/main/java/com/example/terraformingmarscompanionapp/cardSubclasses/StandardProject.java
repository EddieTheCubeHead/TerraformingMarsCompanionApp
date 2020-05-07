package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

/**
 * A bit deprecated subclass for standard projects. Was more useful before the type -variable are
 * most of the functions here. Now just makes creating new standard projects easier.
 */
public abstract class StandardProject extends Card {
    public StandardProject(Game game) {
        super(Type.STANDARD_PROJECT, game);
    }
}
