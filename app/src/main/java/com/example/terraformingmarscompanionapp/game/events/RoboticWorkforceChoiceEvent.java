package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.ui.main.ActivityResourceAddition;

/**
 * An implementation of {@link GameEvent} specifically for playing the card {@link com.example.terraformingmarscompanionapp.cards.corporate_era.cards.RoboticWorkforce}.
 * Represents {@link ResourceChoiceEvent} in that both open a dialogue for choosing a target card
 * for an action
 *
 * @author Eetu Asikainen
 * @version 0.3
 * @since 0.3
 */
public class RoboticWorkforceChoiceEvent implements GameEvent {

    private Player player;

    /**
     * Constructor
     *
     * @param player {@link Player} playing robotic workforce to trigger the event
     */
    public RoboticWorkforceChoiceEvent(Player player) {
        this.player = player;
    }

    @Override
    public void playEvent(Context context) {
        Log.i("Event played", toString());
        Intent intent = new Intent(context, ActivityResourceAddition.class);
        intent.putExtra(ActivityResourceAddition.SPECIAL_CASE, "Robotic workforce");
        intent.putExtra(ActivityResourceAddition.PLAYER_NAME, player.getName());
        context.startActivity(intent);
    }

    /**
     * toString overridden for logging and debugging
     *
     * @return {@link String} a verbal representation of the event
     */
    @NonNull
    @Override
    public String toString() {
        return String.format("Robotic workforce card choice event, player: " + player.getName());
    }
}
