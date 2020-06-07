package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

/**
 * A subclass of {@link Card} to represent standard projexts in game. Most of the functionality was
 * moved to {@link Card#finishPlay(Player)} so the class is now just a helper for constructing
 * standard projects and under consideration of removal alltogether
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public abstract class StandardProject extends Card {

    /**
     * Constuctor for QoL while building standard projects
     *
     * @param game {@link Game} associated with this card
     */
    public StandardProject(Game game) {
        super(Type.STANDARD_PROJECT, game);
    }
}
