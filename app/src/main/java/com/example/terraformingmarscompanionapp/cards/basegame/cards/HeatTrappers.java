package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class HeatTrappers extends Card {
    public HeatTrappers(Game game) {
        super("green");
        name = "Heat trappers";
        price = 6;
        tags.add("energy");
        tags.add("building");
        victory_points = -1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        //TODO poista toiselta kaksi lämpöä
        player.changeEnergyProduction(1);
        super.onPlay(player);
    }
}
