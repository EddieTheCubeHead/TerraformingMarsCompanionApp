package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.webSocket.GameActions;
import com.example.terraformingmarscompanionapp.webSocket.packets.ResourceEventPacket;

/**
 * An implementation of {@link GameEvent} representing a game event where a variable amount
 * of resources are added onto a specified card
 *
 * @author Eetu Asikainen
 * @version 0.3
 * @since 0.3
 */
public class ResourceEvent implements GameEvent {

    private ResourceCard card;
    private Integer change;

    /**
     * Constructor
     *
     * @param card {@link ResourceCard} which resources are changed
     * @param change {@link Integer} the amount of change to the resources of the card
     */
    public ResourceEvent(ResourceCard card, Integer change) {
        this.card = card;
        this.change = change;
    }

    @Override
    public void playEvent(Context context) {
        card.changeResourceAmount(change);
        if (GameController.getServerMultiplayer()) {
            GameActions.sendResourceEvent(new ResourceEventPacket(card.getName(), change));
        }
        EventScheduler.playNextEvent(context);
    }

    /**
     * toString overridden for logging and debugging
     *
     * @return {@link String} a verbal representation of the event
     */
    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
