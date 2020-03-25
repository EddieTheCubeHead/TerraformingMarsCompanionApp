package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class ImmigrationShuttles extends Card {
    public ImmigrationShuttles(Game game) {
        name = "Immigration shuttles";
        price = 31;
        tags.put("earth", 1);
        tags.put("space", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addEarthTag();
        player.addSpaceTag();
        player.changeMoneyProduction(5);
        player.addGreen(this);
        owner_game.updateManager.onVpCardPlayed(player);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints((owner_game.getCitiesOnMars() + owner_game.getCitiesInSpace())/3);
    }
}
