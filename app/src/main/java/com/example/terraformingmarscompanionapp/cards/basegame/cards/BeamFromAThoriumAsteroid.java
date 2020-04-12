package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class BeamFromAThoriumAsteroid extends Card {
    public BeamFromAThoriumAsteroid(Game game) {
        super("green");
        name = "Beam from a thorium asteroid";
        price = 32;
        tags.add("jovian");
        tags.add("space");
        tags.add("energy");
        requirements.put("min_jovian_tags", 1);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        player.changeHeatProduction(3);
        player.changeEnergyProduction(3);
        super.onPlay(player);
    }
}
