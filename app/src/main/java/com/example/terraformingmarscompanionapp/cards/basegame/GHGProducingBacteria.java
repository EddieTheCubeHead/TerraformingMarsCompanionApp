package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class GHGProducingBacteria extends Card {
    public GHGProducingBacteria(Game game) {
        name = "GHG producing bacteria";
        price = 8;
        tags.put("science", 1);
        tags.put("microbe", 1);
        requirements.put("min_oxygen", 4);
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
    public void cardEffect(Player player) {

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
}
