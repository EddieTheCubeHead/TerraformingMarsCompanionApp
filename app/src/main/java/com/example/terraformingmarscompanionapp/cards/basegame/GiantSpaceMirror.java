package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class GiantSpaceMirror extends Card {
    public GiantSpaceMirror(Game game) {
        name = "Giant space mirror";
        price = 17;
        tags.put("energy", 1);
        tags.put("space", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addSpaceTag();
        player.addEnergyTag();
        player.changeEnergyProduction(3);
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
