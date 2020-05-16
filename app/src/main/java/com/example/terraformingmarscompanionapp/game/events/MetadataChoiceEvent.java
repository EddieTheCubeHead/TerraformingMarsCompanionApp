package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;

import java.util.ArrayList;

public final class MetadataChoiceEvent extends GameEvent {
    private String message;
    private ArrayList<String> choices;
    private Card card;
    private ChoiceDialog.USE_CASE use_case;

    public MetadataChoiceEvent(String message, ArrayList<String> choices, Card card, ChoiceDialog.USE_CASE use_case) {
        this.message = message;
        this.choices = choices;
        this.card = card;
        this.use_case = use_case;
    }

    public MetadataChoiceEvent(Card card) {
        this.message = "Choose your target";
        this.card = card;
        this.choices = new ArrayList<String>();

        for (Player player : GameController.getPlayers()) {
            choices.add(player.getName());
        }

        this.use_case = ChoiceDialog.USE_CASE.PLAYER;
    }

    @Override
    public void playEvent(Context context) {
        ChoiceDialog.displayDialog(context, message, use_case, choices, card, GameController.getCurrentPlayer());
    }
}
