package com.example.terraformingmarscompanionapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public final ArrayList<Integer> getTagIntegers() {
        ArrayList<Integer> tag_integers = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : tags.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {

                //TODO Aleksanteri laittaa luvut kuntoon

                if (entry.getKey() == "building") {
                    tag_integers.add(1);
                } else if (entry.getKey() == "space") {
                    tag_integers.add(2);
                } else if (entry.getKey() == "earth") {
                    tag_integers.add(3);
                } else if (entry.getKey() == "city") {
                    tag_integers.add(4);
                } else if (entry.getKey() == "plant") {
                    tag_integers.add(5);
                } else if (entry.getKey() == "microbe") {
                    tag_integers.add(6);
                } else if (entry.getKey() == "science") {
                    tag_integers.add(7);
                } else if (entry.getKey() == "energy") {
                    tag_integers.add(8);
                } else if (entry.getKey() == "jovian") {
                    tag_integers.add(9);
                } else if (entry.getKey() == "venus") {
                    tag_integers.add(10);
                } else if (entry.getKey() == "animal") {
                    tag_integers.add(11);
                } else if (entry.getKey() == "event") {
                    tag_integers.add(12);
                } else if (entry.getKey() == "joker") {
                    tag_integers.add(13);
                }
            }
        }

        return tag_integers;
    }

    @Override
    public final String toString() {
        return name;
    }
}
