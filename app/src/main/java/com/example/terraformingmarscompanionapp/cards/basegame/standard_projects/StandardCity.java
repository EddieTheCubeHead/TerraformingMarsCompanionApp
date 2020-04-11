package com.example.terraformingmarscompanionapp.cards.basegame.standard_projects;

import com.example.terraformingmarscompanionapp.CardSubclasses.StandardProject;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class StandardCity extends StandardProject {
    public StandardCity(Game game) {
        super(game);
        name = "Standard project: City";
        price = 25;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.tile_handler.placeCity(player);
        player.changeMoneyProduction(1);
        super.onPlay(player);
    }
}
