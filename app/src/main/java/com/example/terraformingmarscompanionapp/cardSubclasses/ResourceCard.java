package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.Player;

public abstract class ResourceCard extends Card {
    public ResourceCard(Type type) {
        super(type);
    }

    //Enum resurssityypin määrittämiseen
    public enum ResourceType {
        MICROBE,
        ANIMAL,
        SCIENCE,
        FLOATER,
        PET,
        UNIQUE
    }

    protected ResourceType resource_type;
    protected Integer resource_amount = 0;

    public final ResourceType getResourceType() {return resource_type;}
    public final Integer getResourceAmount() {return resource_amount;}

    public final boolean changeResourceAmount(Integer change_amount) {
        if (resource_amount + change_amount < 0) {
            return false;
        } else {
            resource_amount += change_amount;
            return true;
        }
    }

    public Integer onPlay(Player player) {
        player.addResourceHolder(this);
        super.onPlay(player);
        return null;
    }
}
