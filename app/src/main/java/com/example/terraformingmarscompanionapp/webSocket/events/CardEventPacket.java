package com.example.terraformingmarscompanionapp.webSocket.events;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;

public class CardEventPacket implements PlayablePacket {
    private String card_name;
    private String player_name;
    private Integer metadata;

    public String getCardName() {return card_name;}
    public String getPlayerName() {return player_name;}
    public Integer getMetadata() {return metadata;}

    public CardEventPacket(String card_name, String player_name, Integer metadata) {
        this.card_name = card_name;
        this.player_name = player_name;
        this.metadata = metadata;
    }

    @Override
    public void playPacket() {
        Game game = GameController.getInstance().getGame();
        game.getDeck().get(card_name).playWithMetadata(game.getPlayer(player_name), metadata);
    }
}
