package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class OptimalAerobraking extends Card {
    public OptimalAerobraking(Game game) {
        name = "Optimal aerobraking";
        price = 7;
        tags.put("space", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addPassive(this);
        player.addSpaceTag();
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null) {
            return;
        }
        owner_player.changeMoney(3);
        owner_player.changeHeat(3);
    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
