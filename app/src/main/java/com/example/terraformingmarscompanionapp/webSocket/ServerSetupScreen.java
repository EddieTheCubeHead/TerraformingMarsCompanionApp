package com.example.terraformingmarscompanionapp.webSocket;

/**
 * Interface for the different activities that can be running while creating a game
 */
public interface ServerSetupScreen {
    void playerJoined(String player_name);
    void startGame();
    void settingChanged(GameSetting setting, Boolean value);
    void mapChanged(Integer value);
}
