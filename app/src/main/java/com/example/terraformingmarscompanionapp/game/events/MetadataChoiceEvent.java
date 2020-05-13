package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;

import java.util.ArrayList;

public final class MetadataChoiceEvent extends GameEvent {
    private String message;
    private ArrayList<String> choices;
    private Card card;

    MetadataChoiceEvent(String message, ArrayList<String> choices, Card card) {
        this.message = message;
        this.choices = choices;
        this.card = card;
    }

    @Override
    public void playEvent(Context context) {
        Intent intent = new Intent(context, ChoiceDialog.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.putExtra(ChoiceDialog.CARD_INTENT, card.getName());
        intent.putExtra(ChoiceDialog.TARGETS, choices);
        context.startActivity(intent);
    }
}
