package com.example.terraformingmarscompanionapp.cardSubclasses;

public interface FirstAction {
    void firstAction();
    Boolean firstActionUsed();
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
