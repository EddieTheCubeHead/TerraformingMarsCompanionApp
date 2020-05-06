package com.example.terraformingmarscompanionapp.cards.basegame.standardProjects;

import com.example.terraformingmarscompanionapp.cardSubclasses.StandardProject;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class StandardGreenery extends StandardProject {
    public StandardGreenery(Game game) {
        super(game);
        name = "Standard project: Greenery";
        price = 23;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.GREENERY, owner_game));
        player.addGreenery();
        super.playWithMetadata(player, data);
    }
}
