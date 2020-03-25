package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class RegolithEaters extends Card {
    public RegolithEaters(Game game) {
        name = "Regolith eaters";
        price = 13;
        tags.put("science", 1);
        tags.put("microbe", 1);
        resource_type = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addAction(this);
        player.addScienceTag();
        player.addMicrobeTag();
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
            boolean added_microbes = true;
            //TODO UI kysymään lisääkö mikrobeita vai nostaako happea
            if (added_microbes) {
                resource_amount++;
            } else if (resource_amount <= 2) {
                resource_amount -= 2;
                owner_game.raiseOxygen(owner_player);
            } else {
                return false;
            }
            action_used = true;
            return true;
        }
    }
}
