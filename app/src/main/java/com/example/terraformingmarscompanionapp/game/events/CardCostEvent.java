package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.ui.gameMainElements.dialogues.CardCostDialog;
import com.example.terraformingmarscompanionapp.webSocket.packets.CardCostPacket;

/**
 * A seldom-used implementation of {@link GameEvent} used specifically when a subclass of {@link Card}
 * needs to be played from inside of the playing process of another card with a cost included. For getting
 * the {@link CardCostPacket} representing the used resources. Usually these cards are utility/ghost
 * cards that represent for example actions with cost (see {@link com.example.terraformingmarscompanionapp.cards.basegame.cards.AquiferPumping}).
 * <p></p>
 * Calls {@link CardCostDialog} to get player input about what resorces to use for the cardlike object
 * provided
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public final class CardCostEvent implements GameEvent {

    private Card card;

    /**
     * Constructor
     *
     * @param card {@link Card} that the game needs the used resources for.
     */
    public CardCostEvent(Card card) {
        this.card = card;
    }

    @Override
    public void playEvent(Context context) {
        Log.i("Event played", toString());
        CardCostDialog.displayDialog(context, card, true);
    }

    /**
     * toString overridden for logging and debugging
     *
     * @return {@link String} a verbal representation of the event
     */
    @NonNull
    @Override
    public String toString() {
        return String.format("Card cost event: card: %s", card.getName());
    }
}
