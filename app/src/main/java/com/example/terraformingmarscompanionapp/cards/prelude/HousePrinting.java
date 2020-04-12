package com.example.terraformingmarscompanionapp.cards.prelude;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class HousePrinting extends Card {
    public HousePrinting(Game game) {
        super("green");
        name = "House printing";
        price = 10;
        tags.add("building");
        victory_points = 1;
        owner_game = game;
    }


    @Override
    public void onPlay(Player player) {
        player.changeSteelProduction(1);
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
