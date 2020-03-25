package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class LocalHeatTrapping extends Card {
    public LocalHeatTrapping(Game game) {
        name = "Local heat trapping";
        price = 1;
        tags.put("event", 1);
        requirements.put("min_heat", 5);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addEventTag();
        player.changeHeat(-5);
        boolean added_animals = true;
        //TODO UI kysy kasvien vai eläinten lisäys, jos eläimet, minne?
        if (added_animals) {
            //TODO lisää eläimet haluttuun paikkaan
        } else {
            player.changePlants(4);
        }
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
