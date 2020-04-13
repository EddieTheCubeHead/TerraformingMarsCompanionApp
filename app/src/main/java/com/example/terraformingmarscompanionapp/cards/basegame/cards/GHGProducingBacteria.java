package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class GHGProducingBacteria extends ResourceCard implements ActionCard {
    public GHGProducingBacteria(Game game) {
        super("blue");
        name = "GHG producing bacteria";
        price = 8;
        tags.add("science");
        tags.add("microbe");
        requirements.setMinOxygen(4);
        resource_type = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addScienceTag();
        player.addMicrobeTag();
        player.addAction(this);
        owner_player = player;
    }

    @Override
    public boolean cardAction() {
        if (action_used) {
            return false;
        } else {
            Boolean added_microbes = true;
            //TODO UI kysymään tämä
            if (added_microbes) {
                resource_amount++;
            } else if (resource_amount < 2) {
                return false;
            } else {
                resource_amount -= 2;
            }
            action_used = true;
            return true;
        }
    }

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionUsed() {
        return action_used;
    }
}
