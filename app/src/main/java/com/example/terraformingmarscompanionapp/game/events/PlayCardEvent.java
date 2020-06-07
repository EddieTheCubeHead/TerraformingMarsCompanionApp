package com.example.terraformingmarscompanionapp.game.events;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Player;

/**
 * An implementation of {@link GameEvent} used to represent playing a card or using an action of
 * an already played card.
 */
public final class PlayCardEvent implements GameEvent {
    private Card card;
    private Player player;
    private Integer metadata;

    /**
     * Constructor
     *
     * @param card {@link Card} that is being played
     * @param player {@link Player} that is playing the card
     * @param metadata {@link Integer} metadata used for playing the card. If the card doesn't use metadata any value will work but giving 0 is recommended for consistency
     */
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

    /**
     * toString overridden for logging and debugging
     *
     * @return {@link String} a verbal representation of the event
     */
    @SuppressLint("DefaultLocale")
    @NonNull
    @Override
    public String toString() {
        return String.format("Play card event: card: %s, metadata: %d", card.toString(), metadata);
    }
}
