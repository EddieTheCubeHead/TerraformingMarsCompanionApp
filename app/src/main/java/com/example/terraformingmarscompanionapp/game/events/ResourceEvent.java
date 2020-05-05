package com.example.terraformingmarscompanionapp.game.events;

import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.Game;

public final class ResourceEvent extends GameEvent {
    private ResourceCard.ResourceType resource_type;
    private Game game;
    private Integer amount;

    public ResourceEvent(ResourceCard.ResourceType resource_type, Game game, Integer amount) {
        this.resource_type = resource_type;
        this.game = game;
        this.amount = amount;
    }

    @Override
    public void playEvent() {

    }
}
