package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

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
    public void playEvent(Context context) {
        Log.i("Event played", toString());
        game.tile_handler.getCoordinatesFromPlayer(tile_type, context);
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("Tile event: tile type: %s", tile_type.toString());
    }
}
