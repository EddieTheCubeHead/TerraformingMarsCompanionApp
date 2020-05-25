package com.example.terraformingmarscompanionapp.game.events;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Player;

public final class PlayCardEvent extends GameEvent {
    private Card card;
    private Player player;
    private Integer metadata;

    public PlayCardEvent(Card card, Player player, Integer metadata) {
        this.card = card;
        this.player = player;
        this.metadata = metadata;
    }

    @Override
    public void playEvent(Context context) {
        Log.i("Event played", toString());
        if (card.getOwner() == null) {
            card.onPlayServerHook(player, metadata);
        } else if (card instanceof ActionCard) {
            ((ActionCard) card).actionServerHook(player, metadata);
        } else {
            Log.i("PlayCardEvent Error", "Called from owned card that is not an action card.");
        }
    }

    @SuppressLint("DefaultLocale")
    @NonNull
    @Override
    public String toString() {
        return String.format("Play card event: card: %s, metadata: %d", card.toString(), metadata);
    }
}
