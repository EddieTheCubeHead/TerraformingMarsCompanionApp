package com.example.terraformingmarscompanionapp.cardSubclasses;

/**
 * Some corporations require you to perform a specific action as your first action in the game.
 * They can implement this interface and declare the action here.
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public interface FirstAction {

    /**
     * Represent the action that the player playing with this corporation is required to take as their
     * first action of the first generatio
     */
    void firstAction();

    /**
     * A simple method that returns whether the {@link #firstAction()} is already used
     *
     * @return {@link Boolean}
     */
    Boolean firstActionUsed();
}
