package com.example.terraformingmarscompanionapp.cards.basegame.standard_projects;

import com.example.terraformingmarscompanionapp.cardSubclasses.StandardProject;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class StandardAquifer extends StandardProject {
    public StandardAquifer(Game game) {
        super(game);
        name = "Standard project: Aquifer";
        price = 18;
    }

    @Override
    public Integer onPlay(Player player) {
        owner_game.tile_handler.placeOcean(player);
        return super.onPlay(player);
    }
}
