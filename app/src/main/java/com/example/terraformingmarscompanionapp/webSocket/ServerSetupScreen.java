package com.example.terraformingmarscompanionapp.webSocket;

public interface ServerSetupScreen {
    void playerJoined(String player_name);
    void startGame();
    void settingChanged(GameSetting setting, Boolean value);
    void mapChanged(Integer value);
}
