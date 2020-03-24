package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Soletta extends Card {
    public Soletta(Game game) {
        name = "Soletta";
        price = 35;
        tags.put("space", 35);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addSpaceTag();
        player.changeHeatProduction(7);
        player.addGreen(this);
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
