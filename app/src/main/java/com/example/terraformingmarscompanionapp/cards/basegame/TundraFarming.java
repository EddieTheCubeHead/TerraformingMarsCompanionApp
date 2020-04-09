package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class TundraFarming extends Card {
    public TundraFarming(Game game) {
        super("green");
        name = "Tundra farming";
        price = 16;
        tags.add("plant");
        requirements.put("min_temperature", -6);
        victory_points = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlants(1);
        player.changePlantsProduction(1);
        player.changeMoneyProduction(2);
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
