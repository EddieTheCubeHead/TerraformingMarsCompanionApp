package com.example.terraformingmarscompanionapp.cards.colonies;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class SolarReflectors extends Card {
    public SolarReflectors(Game game) {
        name = "Solar reflectors";
        price = 23;
        tags.put("space", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addSpaceTag();
        player.changeHeatProduction(5);
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
