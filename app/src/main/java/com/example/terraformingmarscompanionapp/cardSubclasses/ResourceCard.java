package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public abstract class ResourceCard extends Card {
    public ResourceCard(Type type, Game game) {
        super(type, game);
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

    public final void changeResourceAmount(Integer change_amount) {resource_amount += change_amount;}

    public void onPlay(Player player) {
        player.addResourceHolder(this);
        super.onPlay(player);
    }
}
