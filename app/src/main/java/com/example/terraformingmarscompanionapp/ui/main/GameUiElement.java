package com.example.terraformingmarscompanionapp.ui.main;

/**
 * In case the logic part needs to display a prompt or handle other UI logic, all activities, fragments
 * etc. implement this so they can be cast to this based on context and show the prompt, etc.
 *
 * Note: once UI rehaul is over and everything is hopefully just a fragment/dialogue in the InGameUi
 * activity this is depricated and can be removed
 */

public interface GameUiElement {
    void displayPrompt(String text);
}
