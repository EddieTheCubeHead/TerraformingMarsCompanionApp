package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class LargeConvoy extends Card {
    public LargeConvoy(Game game) {
        name = "Large convoy";
        price = 36;
        tags.put("space", 1);
        tags.put("earth", 1);
        tags.put("event", 1);
        victory_points = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        boolean chose_plants = true;
        owner_game.updateManager.onSpaceEvent(player);
        player.addEventTag();
        //TODO kasveja 5 tai eläimiä 4 UI
        if (chose_plants) {
            player.changePlants(5);
        } else {
            //TODO lisää 4 eläintä toiselle kortille
        }
        //TODO prompti nostaa kaksi korttia
        player.addRed(this);
        owner_player = player;

    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
