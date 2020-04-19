package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public abstract class StandardProject extends Card {
    public StandardProject(Game game) {
        super(Type.STANDARD_PROJECT);
        owner_game = game;
    }

    //Superin onPlayn override ettei vahingossakaan käytetä Card.onPlayta vakioprojektia luotaessa
    @Override
    public Integer onPlay(Player player) {return null;}
}
