package com.example.terraformingmarscompanionapp.webSocket.events;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;

public class ResourceEventPacket implements PlayablePacket {
    private String card_name;
    private Integer change;

    public ResourceEventPacket(String card_name, Integer change) {
        this.card_name = card_name;
        this.change = change;
    }


    @Override
    public void playPacket() {
       Game game = GameController.getInstance().getGame();
       game.changeCardResources(game.getDeck().get(card_name), change);
    }
}
