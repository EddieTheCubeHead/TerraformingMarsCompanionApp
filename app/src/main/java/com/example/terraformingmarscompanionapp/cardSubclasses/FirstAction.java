package com.example.terraformingmarscompanionapp.cardSubclasses;

/**
 * Some corporations require you to perform a specific action as your first action in the game.
 * They can implement this interface and declare the action here.
 */
public interface FirstAction {
    void firstAction();
    Boolean firstActionUsed();
}
