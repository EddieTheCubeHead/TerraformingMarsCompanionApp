package com.example.terraformingmarscompanionapp.game.events;

import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.Game;

public final class ResourceEvent extends GameEvent {
    private ResourceCard.ResourceType resource_type;
    private Game game;

    public ResourceEvent(ResourceCard.ResourceType resource_type, Game game) {
        this.resource_type = resource_type;
        this.game = game;
    }

    @Override
    public void playEvent() {

    }
}
