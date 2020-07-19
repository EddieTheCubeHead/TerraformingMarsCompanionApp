package com.example.terraformingmarscompanionapp.webSocket.packets;

import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.player.Player;

public class RoboticWorkforcePacket implements ServerPacket {

    private String card_name;
    private String player_name;
    private Integer metadata;

    public RoboticWorkforcePacket(String card_name, String player_name, Integer metadata){
        this.card_name = card_name;
        this.player_name = player_name;
        this.metadata = metadata;
    }

    @Override
    public void playPacket() {

        Card card = GameController.getGame().getAllCards().get(card_name);
        Player player = GameController.getPlayer(player_name);

        card.getProductionBox().playProductionBox(player, metadata);
    }
}
