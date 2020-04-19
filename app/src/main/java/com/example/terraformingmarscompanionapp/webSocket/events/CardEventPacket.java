package com.example.terraformingmarscompanionapp.webSocket.events;

public class CardEventPacket {
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
}
