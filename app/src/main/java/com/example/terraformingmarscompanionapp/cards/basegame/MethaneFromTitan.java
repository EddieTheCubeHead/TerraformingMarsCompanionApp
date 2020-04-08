package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class MethaneFromTitan extends Card {
    public MethaneFromTitan(Game game) {
        super("green");
        name = "Methane from titan";
        price = 10;
        tags.add("jovian");
        tags.add("space");
        requirements.put("min_oxygen", 2);
        victory_points = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeHeatProduction(2);
        player.changePlantsProduction(2);
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
