package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Shuttles extends Card {
    public Shuttles(Game game) {
        super("blue");
        name = "Shuttles";
        price = 10;
        tags.add("space");
        requirements.put("min_oxygen", 5);
        requirements.put("min_energy_production", 1);
        victory_points = 0;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeSpaceTagDiscount(2);
        player.changeEnergyProduction(-1);
        player.changeMoneyProduction(2);
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
