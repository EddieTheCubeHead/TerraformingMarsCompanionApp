package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class SoilFactory extends Card {
    public SoilFactory(Game game) {
        name = "Soil factory";
        price = 9;
        tags.put("building", 1);
        requirements.put("min_energy_production", 1);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addBuildingTag();
        if (!player.changeEnergyProduction(-1)) {
            System.out.println("Virhe kortin vaatimusten tarkistamisessa.");
        }
        player.changePlantsProduction(1);
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
