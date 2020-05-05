package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public abstract class StandardProject extends Card {
    public StandardProject(Game game) {
        super(Type.STANDARD_PROJECT, game);
    }

    //Superin onPlayn override ettei vahingossakaan käytetä Card.onPlayta vakioprojektia luotaessa
    @Override
    public void playWithMetadata(Player player, Integer data) {}
}
