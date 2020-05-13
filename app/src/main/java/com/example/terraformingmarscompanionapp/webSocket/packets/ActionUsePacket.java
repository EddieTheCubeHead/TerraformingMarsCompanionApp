package com.example.terraformingmarscompanionapp.webSocket.packets;

import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;

public final class ActionUsePacket implements ServerPacket {
    private Boolean end_turn;
    private Boolean use_action;

    public ActionUsePacket(Boolean end_turn, Boolean use_action) {
        this.end_turn = end_turn;
        this.use_action = use_action;
    }

    public ActionUsePacket(Boolean end_turn) {
        this.end_turn = false;
        use_action = true;
    }

    @Override
    public void playPacket() {
        if (use_action) {
            GameController.useAction(end_turn);
        } else if (end_turn) {
            GameController.endTurn();
        }
    }
}
