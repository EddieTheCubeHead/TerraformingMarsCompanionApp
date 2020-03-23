package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Decomposers extends Card {
    public Decomposers(Game game) {
        name = "Decomposers";
        price = 5;
        tags.put("microbe", 1);
        requirements.put("min_oxygen", 3);
        resource_type = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_player = player;
        player.addPassive(this);
        player.addMicrobeTag();
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        } else if (owner_player == player) {
            resource_amount++;
        }
    }

    @Override
    public boolean cardAction() {
        return false;
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount/3);
    }
}
