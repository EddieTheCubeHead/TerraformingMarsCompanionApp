package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog;

import java.util.ArrayList;

/**
 * An implementation of {@link GameEvent} used when a card requires the player to choose between
 * two or more non-numeric options, for example a target player for an action. The event calls
 * {@link ChoiceDialog#displayDialog(Context, String, ChoiceDialog.USE_CASE, ArrayList, Card, Player)}
 * to gather the needed data from the player.
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public final class MetadataChoiceEvent implements GameEvent {
    private String message;
    private ArrayList<String> choices;
    private Card card;
    private ChoiceDialog.USE_CASE use_case;

    /**
     * The main constructor, used when more precise declaration of the possible choices and the displayed
     * message needs to be known.
     *
     * @param message {@link String} message that should be displayed to tha player
     * @param choices {@link ArrayList} of {@link String} representing all the possible choices. Be careful with the order and metadata (see {@link ChoiceDialog}
     * @param card {@link Card} that the event is gathering data to play
     * @param use_case {@link com.example.terraformingmarscompanionapp.ui.playDialogues.ChoiceDialog.USE_CASE} the usecase the card is called with. Most often GENERAL
     */
    public MetadataChoiceEvent(String message, ArrayList<String> choices, Card card, ChoiceDialog.USE_CASE use_case) {
        this.message = message;
        this.choices = choices;
        this.card = card;
        this.use_case = use_case;
    }

    /**
     * A Quality of Life constructor for the most common usecase of the event: getting a target player
     * for a card.
     *
     * @param card {@link Card} that the event is gathering data to play
     */
    public MetadataChoiceEvent(Card card) {
        this.message = "Choose your target";
        this.card = card;
        this.choices = new ArrayList<>();

        for (Player player : GameController.getPlayers()) {
            choices.add(player.getName());
        }

        this.use_case = ChoiceDialog.USE_CASE.PLAYER;
    }

    @Override
    public void playEvent(Context context) {
        Log.i("Event played", toString());
        ChoiceDialog.displayDialog(context, message, use_case, choices, card, GameController.getCurrentPlayer());
    }

    /**
     * toString overridden for logging and debugging
     *
     * @return {@link String} a verbal representation of the event
     */
    @NonNull
    @Override
    public String toString() {
        return String.format("Metadata choice event: card: %s, use case: %s", card.getName(), use_case.toString());
    }
}
