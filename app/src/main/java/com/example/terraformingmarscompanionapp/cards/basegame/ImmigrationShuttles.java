package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class ImmigrationShuttles extends Card {
    public ImmigrationShuttles(Game game) {
        super("green");
        name = "Immigration shuttles";
        price = 31;
        tags.add("earth");
        tags.add("space");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeMoneyProduction(5);
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints((owner_game.getCitiesOnMars() + owner_game.getCitiesInSpace())/3);
    }
}
