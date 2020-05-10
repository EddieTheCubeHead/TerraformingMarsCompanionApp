package com.example.terraformingmarscompanionapp.webSocket.events;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.GameController;

/**
 * Representing adding or removing resources from a card
 */
public class ResourceEventPacket implements ServerPacket {
    private String card_name;
    private Integer change;

    public ResourceEventPacket(String card_name, Integer change) {
        this.card_name = card_name;
        this.change = change;
    }


    @Override
    public void playPacket() {
       Card card = GameController.getGame().getDeck().get(card_name);
       if (card instanceof ResourceCard) {
           ResourceCard resource_holder = (ResourceCard)card;
           resource_holder.changeResourceAmount(change);
       }
    }
}
