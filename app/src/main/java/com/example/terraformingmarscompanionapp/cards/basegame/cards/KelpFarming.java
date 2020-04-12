package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class KelpFarming extends Card {
    public KelpFarming(Game game) {
        super("green");
        name = "Kelp farming";
        price = 17;
        tags.add("plant");
        requirements.put("min_oceans", 6);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeMoneyProduction(2);
        player.changePlantsProduction(3);
        player.changePlants(2);
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
