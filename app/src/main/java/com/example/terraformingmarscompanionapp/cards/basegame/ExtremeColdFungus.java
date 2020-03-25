package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class ExtremeColdFungus extends Card {
    public ExtremeColdFungus(Game game) {
        name = "Extreme-cold fungus";
        price = 13;
        tags.put("microbe", 1);
        requirements.put("max_temperature", -10);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
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
            Boolean chose_microbes = true;
            //TODO UI kasveja vai mikrobeja UI
            if (chose_microbes) {
                //TODO lisää toiselle kortilla kaksi mikrobia
            } else {
                owner_player.changePlants(1);
            }
            action_used = true;
            return true;
        }
    }
}
