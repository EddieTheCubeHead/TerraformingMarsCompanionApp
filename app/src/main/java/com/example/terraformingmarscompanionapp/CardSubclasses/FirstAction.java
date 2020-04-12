package com.example.terraformingmarscompanionapp.CardSubclasses;

public interface FirstAction {
    void firstAction();
}

/* Rajapinnan käyttö pelin alussa:
    public void test() {
        for (Player player : players) {
            if (player.getCorporation() instanceof FirstAction) {
                FirstAction action = (FirstAction)player.getCorporation();
                action.firstAction();
            }
        }
    }
 */
