package com.example.terraformingmarscompanionapp.cards.basegame.standard_projects;

import com.example.terraformingmarscompanionapp.CardSubclasses.StandardProject;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class StandardGreenery extends StandardProject {
    public StandardGreenery(Game game) {
        super(game);
        name = "Standard project: Greenery";
        price = 23;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.tile_handler.placeGreenery(player);
        super.onPlay(player);
    }
}
