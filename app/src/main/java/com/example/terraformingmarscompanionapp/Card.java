package com.example.terraformingmarscompanionapp;

import java.util.HashMap;

public abstract class Card {
    protected Game owner_game;
    protected String name = "ABSTRACT_BASE_CARD";
    protected Integer price = 0;
    protected Integer victory_points = 0;
    protected Boolean action_used = false;
    protected Player owner_player = null; //Omistava pelaaja, null jos pelaamaton
    protected HashMap<String, Integer> tags;
    protected HashMap<String, Integer> requirements;
    protected Integer resource_type = 0;
    protected Integer resource_amount = 0;
    //0: tyhjä, 1: mikrobi, 2: eläin, 3: tiede, 4: floater, 5: uniikki, 6: lemmikki

    public abstract void onPlay (Player player);
    public abstract void cardEffect (Player player);
    public abstract boolean cardAction ();

    public void onGameEnd() {owner_player.changeVictoryPoints(victory_points);}
    public final void takeResources(Integer amount) {resource_amount -= amount;}
    public final String getName() {return name;}
    public final Integer getPrice() {return price;}
    public final HashMap<String, Integer> getTags() {return tags;}
    public final HashMap<String, Integer> getRequirements() {return requirements;}
    public final Integer getResourceType() {return resource_type;}
    public final Integer getResourceAmount() {return resource_amount;}
    public final void resetActionUsed() {action_used = false;}
    public final boolean changeResourceAmount(Integer change_amount) {
        if (resource_amount + change_amount < 0) {
            return false;
        } else {
            resource_amount += change_amount;
            return true;
        }
    }

    @Override
    public final String toString() {
        return name;
    }
}
