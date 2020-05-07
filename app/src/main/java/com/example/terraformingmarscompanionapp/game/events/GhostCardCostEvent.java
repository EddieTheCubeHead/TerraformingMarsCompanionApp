package com.example.terraformingmarscompanionapp.game.events;

import com.example.terraformingmarscompanionapp.InGameUI;
import com.example.terraformingmarscompanionapp.SearchActivity;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.ui.main.CardCostDialog;
import com.example.terraformingmarscompanionapp.webSocket.events.CardCostPacket;

import java.util.ArrayList;

//Käyttää haamukortteja kortin hinnan selvittämiseen
public final class GhostCardCostEvent extends GameEvent {
    private CardCostPacket packet;

    private Card card;

    public GhostCardCostEvent(Card card) {
        this.card = card;
    }

    @Override
    public void playEvent() {
        CardCostDialog.displayDialog(GameController.getInstance().getContext(), card, true);
    }
}
