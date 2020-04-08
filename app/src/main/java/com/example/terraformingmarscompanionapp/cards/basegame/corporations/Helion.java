package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Helion extends Card {
    public Helion(Game game) {
        name = "Helion";
        tags.put("space", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addSpaceTag();
        player.changeMoney(42);
        player.changeHeatProduction(3);
        player.setHeatIsMoney(true);
        player.setCorporation(this);
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
