package com.example.terraformingmarscompanionapp.game.events;

import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.webSocket.events.CardCostPacket;

import java.util.ArrayList;

public final class CostEvent extends GameEvent {
    private CardCostPacket packet;

    private ArrayList<Tag> tags;
    private Integer price;

    public CostEvent(ArrayList<Tag> tags, Integer price) {
        this.tags = tags;
        this.price = price;
    }

    @Override
    public void playEvent() {
    }
}
