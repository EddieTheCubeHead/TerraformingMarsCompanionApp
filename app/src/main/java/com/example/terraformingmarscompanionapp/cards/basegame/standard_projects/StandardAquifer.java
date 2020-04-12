package com.example.terraformingmarscompanionapp.cards.basegame.standard_projects;

import com.example.terraformingmarscompanionapp.CardSubclasses.StandardProject;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class StandardAquifer extends StandardProject {
    public StandardAquifer(Game game) {
        super(game);
        name = "Standard project: Aquifer";
        price = 18;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.tile_handler.placeOcean(player);
        super.onPlay(player);
    }
}
