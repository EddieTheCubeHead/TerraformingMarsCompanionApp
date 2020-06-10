package com.example.terraformingmarscompanionapp.cardSubclasses;

import android.content.Context;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

/**
 * A subclass of {@link Card} to represent cards that hold special resources on them during the game.
 * Cards that have resources (animals, microbes, etc.) on them should extend this instead of the base
 * Card -class. Adds functionality and variables needed for representing and managing the resources.
 *
 * @author Eetu Asikainen
 * @version 0.2
 * @since 0.2
 */
public abstract class ResourceCard extends Card {
    public ResourceCard(Type type, Game game) {
        super(type, game);
    }

    /**
     * Enum to indicate the type of the resource stored on the card
     */
    public enum ResourceType {
        MICROBE,
        ANIMAL,
        SCIENCE,
        FLOATER,
        PET,
        UNIQUE,
        EXISTING // Used with CEOs favourite project
    }

    protected ResourceType resource_type;
    protected Integer resource_amount = 0;

    /**
     * @return {@link ResourceCard.ResourceType} the resource type stored on the card
     */
    public final ResourceType getResourceType() {
        return resource_type;
    }

    /**
     * @return {@link Integer} the amount of resources on the card.
     */
    public final Integer getResourceAmount() {
        return resource_amount;
    }

    /**
     * @param change_amount {@link Integer} the amount of resources to change
     */
    public final void changeResourceAmount(Integer change_amount) {resource_amount += change_amount;}
}
