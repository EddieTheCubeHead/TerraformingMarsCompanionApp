package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class ArcticAlgae extends Card {
    public ArcticAlgae(Game game) {
        name = "Arctic algae";
        price = 12;
        tags.put("plant", 1);
        requirements.put("max_temperature", -12);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addPlantTag();
        player.changePlants(1);
        player.addPassive(this);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        }
        owner_player.changePlants(2);
    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
