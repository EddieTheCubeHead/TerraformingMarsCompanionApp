package com.example.terraformingmarscompanionapp;

import java.util.HashMap;

public abstract class Card {
    protected Game owner_game;
    protected String name;
    protected Integer price;
    protected Integer victory_points = null;
    protected Player owner_player = null; //Omistava pelaaja, null jos pelaamaton
    protected HashMap<String, Integer> tags;
    protected HashMap<String, Integer> requirements;

    public abstract void onPlay (Player player);
    public abstract void cardEffect (Player player);
    public abstract boolean cardAction (Player player);

    public String getName() {return name;}
    public Integer getPrice() {return price;}
    public HashMap<String, Integer> getTags() {return tags;}
    public HashMap<String, Integer> getRequirements() {return requirements;}
}
