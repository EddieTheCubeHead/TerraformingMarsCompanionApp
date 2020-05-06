package com.example.terraformingmarscompanionapp.cards.basegame.standardProjects;

import com.example.terraformingmarscompanionapp.cardSubclasses.StandardProject;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class StandardCity extends StandardProject {
    public StandardCity(Game game) {
        super(game);
        name = "Standard project: City";
        price = 25;
        wait_for_server = true;
    }

    @Override
    public void onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.CITY, owner_game));
        super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeMoneyProduction(1);
        super.playWithMetadata(player, data);
    }
}
