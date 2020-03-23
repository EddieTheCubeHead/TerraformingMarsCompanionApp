package com.example.terraformingmarscompanionapp.cards.prelude;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class HousePrinting extends Card {
    public HousePrinting(Game game) {
        name = "House printing";
        price = 10;
        tags.put("building", 1);
        victory_points = 1;
        owner_game = game;
    }


    @Override
    public void onPlay(Player player) {
        player.addBuildingTag();
        player.changeSteelProduction(1);
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
