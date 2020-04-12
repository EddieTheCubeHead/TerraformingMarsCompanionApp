package com.example.terraformingmarscompanionapp.CardSubclasses;

import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public abstract class StandardProject extends Card {
    public StandardProject(Game game) {
        super("standard");
        owner_game = game;
    }

    //Superin onPlayn override ettei vahingossakaan käytetä Card.onPlayta vakioprojektia luotaessa
    @Override
    public void onPlay(Player player) {}
}
