package com.example.terraformingmarscompanionapp.webSocket.events;

//Paketti, joka kertoo mitä kaikkea vuoron aikana tapahtui
public class TurnActionInfoPacket {
    private Boolean card_is_action;
    private Integer tile_event_count;
    private Integer resource_event_count;

    public Boolean getCardIsAction() {return card_is_action;}
    public Integer getTileEventCount() {return tile_event_count;}
    public Integer getResourceEventCount() {return resource_event_count;}

    public void setCardIsAction(Boolean value) {card_is_action = value;}
    public void setTileEventCount(Integer value) {tile_event_count = value;}
    public void setResourceEventCount(Integer value) {resource_event_count = value;}
    public void addResourceEvent() {resource_event_count++;}

    public TurnActionInfoPacket(Boolean card_is_action) {
        this.card_is_action = card_is_action;
    }
}
