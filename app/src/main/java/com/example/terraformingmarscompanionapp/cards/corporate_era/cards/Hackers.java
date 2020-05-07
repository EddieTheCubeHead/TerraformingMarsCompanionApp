package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Hackers extends Card {
    public Hackers(Game game) {
        super(Type.GREEN, game);
        name = "Asteroid mining consortium";
        price = 3;
        victory_points = -1;
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeEnergyProduction(-1);
        player.changeMoneyProduction(2);
        //TODO Poista toiselta pelaajalta 2 rahan tuotantoa
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
