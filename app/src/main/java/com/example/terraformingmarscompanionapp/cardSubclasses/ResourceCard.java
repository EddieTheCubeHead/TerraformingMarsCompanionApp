package com.example.terraformingmarscompanionapp.cardSubclasses;

import com.example.terraformingmarscompanionapp.game.Player;

public abstract class ResourceCard extends Card {
    public ResourceCard(String type) {
        super(type);
    }

    protected Integer resource_type = 0;
    protected Integer resource_amount = 0;
    //0: tyhjä, 1: mikrobi, 2: eläin, 3: tiede, 4: floater, 5: uniikki, 6: lemmikki

    public final Integer getResourceType() {return resource_type;}
    public final Integer getResourceAmount() {return resource_amount;}

    public final boolean changeResourceAmount(Integer change_amount) {
        if (resource_amount + change_amount < 0) {
            return false;
        } else {
            resource_amount += change_amount;
            return true;
        }
    }

    public void onPlay(Player player) {
        player.addResourceHolder(this);
        super.onPlay(player);
    }
}
