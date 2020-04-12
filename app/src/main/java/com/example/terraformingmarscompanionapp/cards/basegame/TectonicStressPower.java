package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class TectonicStressPower extends Card {
    public TectonicStressPower(Game game) {
        super("green");
        name = "Tectonic stress power";
        price = 18;
        tags.add("energy");
        tags.add("building");
        requirements.put("min_science_tags", 2);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        player.changeEnergyProduction(3);
        super.onPlay(player);
    }
}
