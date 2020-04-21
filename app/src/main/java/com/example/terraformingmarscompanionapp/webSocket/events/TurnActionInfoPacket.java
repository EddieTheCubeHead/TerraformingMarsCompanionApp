package com.example.terraformingmarscompanionapp.webSocket.events;

//Paketti, joka kertoo mitä kaikkea vuoron aikana tapahtui
public class TurnActionInfoPacket {
    private Boolean card_is_action = false;
    private Integer tile_event_count = 0;
    private Integer resource_event_count = 0;

    public Boolean getCardIsAction() {return card_is_action;}
    public Integer getTileEventCount() {return tile_event_count;}
    public Integer getResourceEventCount() {return resource_event_count;}

    public void setCardIsAction(Boolean value) {card_is_action = value;}
    public void changeTileEventCount(Integer value) {tile_event_count += value;}
    public void changeResourceEventCount(Integer value) {resource_event_count += value;}
    public void addResourceEvent() {resource_event_count++;}

    public void resetActions() {
        card_is_action = false;
        tile_event_count = 0;
        resource_event_count = 0;
    }
}
