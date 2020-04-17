package com.example.terraformingmarscompanionapp.webSocket.events;

public class CardEventPacket {
    private String card_name;
    private String player_name;
    private Boolean is_action;
    private Integer metadata;
    private Integer tile_events;
    private Integer resource_events;

    public String getCardName() {return card_name;}
    public String getPlayerName() {return player_name;}
    public Boolean getIsAction() {return is_action;}
    public Integer getMetadata() {return metadata;}
    public Integer getTileEventAmount() {return tile_events;}
    public Integer getResourceEventAmount() {return tile_events;}

    public void setMetadata(Integer value) {metadata = value;}
    public void setTileEvents(Integer value) {tile_events = value;}
    public void setResourceEvents(Integer value) {resource_events = value;}

    public CardEventPacket(String card_name, String player_name, Boolean is_action) {
        this.card_name = card_name;
        this.player_name = player_name;
        this.is_action = is_action;
    }
}
