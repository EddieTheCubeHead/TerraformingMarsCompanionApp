package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.packets.ActionUsePacket;

public final class ActionUseEvent extends GameEvent {
    private ActionUsePacket packet;

    public ActionUseEvent(ActionUsePacket packet) {
        this.packet = packet;
    }

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

    //Used for debugging purposes
    @NonNull
    @Override
    public String toString() {
        return String.format("Action use event: %s", packet.toString());
    }
}
