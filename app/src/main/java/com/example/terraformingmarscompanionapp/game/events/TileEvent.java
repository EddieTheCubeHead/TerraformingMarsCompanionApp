package com.example.terraformingmarscompanionapp.game.events;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

/**
 * UI event for placing a tile
 */
public final class TileEvent extends GameEvent {
    private Placeable tile_type;
    private Game game;
    private Player player;

    public TileEvent(Placeable tile_type, Game game) {
        this.tile_type = tile_type;
        this.game = game;
    }

    @Override
    public void playEvent() {
        game.tile_handler.getCoordinatesFromPlayer(tile_type);
    }
}
