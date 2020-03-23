package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Shuttles extends Card {
    public Shuttles(Game game) {
        name = "Shuttles";
        price = 10;
        tags.put("space", 1);
        requirements.put("min_oxygen", 5);
        requirements.put("min_energy_production", 1);
        victory_points = 0;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeSpaceTagDiscount(2);
        if (!player.changeEnergyProduction(-1)) {
            System.out.println("Virhe korttien vaatimusten tarkistamisessa.");
        }
        player.changeMoneyProduction(2);
        player.addSpaceTag();
        player.addPassive(this);
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
