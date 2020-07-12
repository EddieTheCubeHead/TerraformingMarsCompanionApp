package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.packets.ActionUsePacket;

/**
 * An implementations of {@link GameEvent} that represents the last event associated with one in-game
 * action: updating the actions used by the player.
 *
 * @author Eetu Asikainen
 * @version 0.3
 * @since 0.2
 */
public final class ActionUseEvent implements GameEvent {
    private ActionUsePacket packet;

    /**
     * The default constructor. Should be used when finetuning the way the action should be used is
     * necessary
     *
     * @param packet {@link ActionUsePacket} that represents the way actions are used
     */
    public ActionUseEvent(ActionUsePacket packet) {
        this.packet = packet;
    }

    /**
     * A Quality of Life constructor for the most often used way to use an action. (Use action, don't
     * end a turn)
     */
    public ActionUseEvent() {
        this.packet = new ActionUsePacket();
    }

    @Override
    public void playEvent(Context context) {
        Log.i("Event played", toString());
        packet.playPacket();
        if (GameController.getServerMultiplayer()) {
            GameActions.sendActionUse(packet);
        }
    }

    /**
     * To string overridden for logging and debugging
     *
     * @return {@link String} a verbal representation of the event
     */
    @NonNull
    @Override
    public String toString() {
        return String.format("Action use event: %s", packet.toString());
    }
}
