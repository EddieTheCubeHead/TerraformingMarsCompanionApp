package com.example.terraformingmarscompanionapp;

import java.util.HashMap;

public abstract class Card {
    protected Game owner_game;
    protected String name;
    protected Integer price;
    protected Integer victory_points = 0;
    protected Player owner_player = null; //Omistava pelaaja, null jos pelaamaton
    protected HashMap<String, Integer> tags;
    protected HashMap<String, Integer> requirements;
    protected Integer resource_type = 0;
    protected Integer resource_amount = 0;
    //0: tyhjä, 1: mikrobi, 2: eläin, 3: tiede, 4: floater, 5: uniikki

    public abstract void onPlay (Player player);
    public abstract void cardEffect (Player player);
    public abstract boolean cardAction ();

    public void onGameEnd() {owner_player.changeVictoryPoints(victory_points);}
    public String getName() {return name;}
    public Integer getPrice() {return price;}
    public HashMap<String, Integer> getTags() {return tags;}
    public HashMap<String, Integer> getRequirements() {return requirements;}
    public Integer getResourceType() {return resource_type;}
    public Integer getResourceAmount() {return resource_amount;}
    public boolean changeResourceAmount(Integer change_amount) {
        if (resource_amount + change_amount < 0) {
            return false;
        } else {
            resource_amount += change_amount;
            return true;
        }
    }
}
