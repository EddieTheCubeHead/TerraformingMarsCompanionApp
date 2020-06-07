package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

/**
 * An implementation of the {@link GameEvent} used to get the data of placing a tile from player
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public final class TileEvent implements GameEvent {
    private Placeable tile_type;
    private Game game;

    /**
     * Constructor
     *
     * @param tile_type {@link Placeable} the type of the tile player is placing
     * @param game {@link Game} the game the event is associated with
     */
    public TileEvent(Placeable tile_type, Game game) {
        this.tile_type = tile_type;
        this.game = game;
    }

    @Override
    public void playEvent(Context context) {
        Log.i("Event played", toString());
        game.tile_handler.getCoordinatesFromPlayer(tile_type, context);
    }

    /**
     * toString overridden for logging and debugging
     *
     * @return {@link String} a verbal representation of the event
     */
    @NonNull
    @Override
    public String toString() {
        return String.format("Tile event: tile type: %s", tile_type.toString());
    }
}
