package com.example.terraformingmarscompanionapp.cards.basegame.standard_projects;

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
    }

    @Override
    public Integer onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.CITY, owner_game));
        player.changeMoneyProduction(1);
        player.addCity();
        GameController.getInstance().executeNextEvent();
        return super.onPlay(player);
    }
}
