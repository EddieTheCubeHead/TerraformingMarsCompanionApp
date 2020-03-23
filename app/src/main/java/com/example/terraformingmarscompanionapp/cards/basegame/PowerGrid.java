package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class PowerGrid extends Card {
    public PowerGrid(Game game) {
        name = "Power grid";
        price = 18;
        tags.put("energy", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addEnergyTag();
        player.changeEnergyProduction(player.getEnergyTags());
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
