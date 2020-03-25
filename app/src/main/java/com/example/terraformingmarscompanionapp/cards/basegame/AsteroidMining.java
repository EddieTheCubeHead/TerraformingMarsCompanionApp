package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class AsteroidMining extends Card {
    public AsteroidMining(Game game) {
        name = "Asteroid mining";
        price = 30;
        tags.put("space", 1);
        tags.put("jovian", 1);
        victory_points = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addSpaceTag();
        player.addJovianTag();
        player.changeTitaniumProduction(2);
        player.addGreen(this);
        owner_player = player;
        owner_game.updateManager.onVpCardPlayed(player);
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
