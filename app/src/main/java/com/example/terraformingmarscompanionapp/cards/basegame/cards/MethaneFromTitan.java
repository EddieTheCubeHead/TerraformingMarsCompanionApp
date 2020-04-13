package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class MethaneFromTitan extends Card {
    public MethaneFromTitan(Game game) {
        super("green");
        name = "Methane from titan";
        price = 10;
        tags.add("jovian");
        tags.add("space");
        requirements.setMinOxygen(2);
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
