package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.packets.TileEventPacket;

/**
 * An implementation of {@link GameEvent} representing a tile getting placed
 *
 * @author Eetu Asikainen
 * @version 0.3
 * @since 0.3
 */
public class TileEvent implements GameEvent {

    private TileEventPacket packet;

    /**
     * Constructor
     *
     * @param packet {@link TileEventPacket} representing the event being played
     */
    public TileEvent(TileEventPacket packet) {
        this.packet = packet;
    }

    @Override
    public void playEvent(Context context) {
        if (GameController.getServerMultiplayer()) {
            GameActions.sendTileEvent(packet);
        }
        packet.playPacket();
        EventScheduler.playNextEvent(context);
    }

    /**
     * toString overridden for logging and debugging
     *
     * @return {@link String} a verbal representation of the event
     */
    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
