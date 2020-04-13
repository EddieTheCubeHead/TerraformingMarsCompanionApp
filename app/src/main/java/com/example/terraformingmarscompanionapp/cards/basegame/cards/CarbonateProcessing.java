package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class CarbonateProcessing extends Card {
    public CarbonateProcessing(Game game) {
        super("green");
        name = "Carbonate processing";
        price = 6;
        tags.add("building");
        requirements.setMinEnergyProduction(1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(-1);
        player.changeHeatProduction(3);
        super.onPlay(player);
    }
}
