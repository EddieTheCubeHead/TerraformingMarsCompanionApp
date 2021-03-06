package com.example.terraformingmarscompanionapp.game.events;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.terraformingmarscompanionapp.game.cardClasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.ui.main.ActivityResourceAddition;

/**
 * An implementation of {@link GameEvent} used for getting the target of a card resource change from
 * the player
 *
 * @author Eetu Asikainen
 * @version 0.3
 * @since 0.2
 */
public final class ResourceChoiceEvent implements GameEvent {
    private ResourceCard.ResourceType resource_type;
    private Player player;
    private Integer amount;
    private Boolean own_card_only = false;
    private Boolean robotic_workforce = false;

    /**
     * Default constructor
     *
     * @param resource_type {@link ResourceCard.ResourceType} the type of the resource to change
     * @param player {@link Player} that is playing the card triggering the resource change operation
     * @param amount {@link Integer} the amount of resource to change. Can be negative to substract resources instead of adding them
     * @param own_card_only {@link Boolean} whether the operation can only target the playing player's cards
     */
    public ResourceChoiceEvent(ResourceCard.ResourceType resource_type, Player player, Integer amount, Boolean own_card_only) {
        this.resource_type = resource_type;
        this.player = player;
        this.amount = amount;
        this.own_card_only = own_card_only;
    }

    /**
     * A Quality of Life constructor for cards that can target all resource holders, not only those
     * owned by the playing player.
     *
     * @param resource_type {@link ResourceCard.ResourceType} the type of the resource to change
     * @param player {@link Player} that is playing the card triggering the resource change operation
     * @param amount {@link Integer} the amount of resource to change. Can be negative to substract resources instead of adding them
     */
    public ResourceChoiceEvent(ResourceCard.ResourceType resource_type, Player player, Integer amount) {
        this.resource_type = resource_type;
        this.player = player;
        this.amount = amount;
    }

    @Override
    public void playEvent(Context context) {
        Log.i("Event played", toString());
        Intent intent = new Intent(context, ActivityResourceAddition.class);
        intent.putExtra(ActivityResourceAddition.RESOURCE_TYPE, resource_type.toString());
        intent.putExtra(ActivityResourceAddition.AMOUNT, amount);
        intent.putExtra(ActivityResourceAddition.OWNER_ONLY, own_card_only);
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
        return String.format("Resource event: resource type: %s, resource amount: %d", resource_type.toString(), amount);
    }
}
