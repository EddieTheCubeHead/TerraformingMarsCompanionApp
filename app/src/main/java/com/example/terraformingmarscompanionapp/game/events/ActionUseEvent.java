package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;

import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.packets.ActionUsePacket;

public final class ActionUseEvent extends GameEvent {
    private ActionUsePacket packet;

    public ActionUseEvent(ActionUsePacket packet) {
        this.packet = packet;
    }

    @Override
    public void playEvent(Context context) {
        packet.playPacket();
        if (GameController.getServerMultiplayer()) {
            GameActions.sendActionUse(packet);
        }
    }
}
