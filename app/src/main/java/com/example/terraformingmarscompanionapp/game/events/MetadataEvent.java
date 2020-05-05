package com.example.terraformingmarscompanionapp.game.events;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;

public final class MetadataEvent extends GameEvent {
    private MetadataType type;
    private Card card;

    public MetadataEvent(MetadataType type, Card card) {
        this.type = type;
        this.card = card;
    }

    @Override
    public void playEvent() {

    }

    public enum MetadataType {
        PLAYER,
        NUMBER,
        CHOICE
    }
}
