package com.example.terraformingmarscompanionapp.cards.basegame.standard_projects;

import com.example.terraformingmarscompanionapp.cardSubclasses.StandardProject;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class StandardGreenery extends StandardProject {
    public StandardGreenery(Game game) {
        super(game);
        name = "Standard project: Greenery";
        price = 23;
    }

    @Override
    public Integer onPlay(Player player) {
        owner_game.tile_handler.placeGreenery(player);
        player.addGreenery();
        return super.onPlay(player);
    }
}
