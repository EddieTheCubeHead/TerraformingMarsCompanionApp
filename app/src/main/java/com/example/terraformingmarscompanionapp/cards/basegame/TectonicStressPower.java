package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class TectonicStressPower extends Card {
    public TectonicStressPower(Game game) {
        name = "Tectonic stress power";
        price = 18;
        tags.put("energy", 1);
        tags.put("building", 1);
        requirements.put("min_science_tags", 2);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.updateManager.onVpCardPlayed(player);
        player.addEnergyTag();
        player.addBuildingTag();
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
