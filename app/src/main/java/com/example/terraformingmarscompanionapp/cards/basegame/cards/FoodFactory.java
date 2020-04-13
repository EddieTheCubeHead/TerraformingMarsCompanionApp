package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class FoodFactory extends Card {
    public FoodFactory(Game game) {
        super("green");
        name = "Food factory";
        price = 12;
        tags.add("buildilng");
        requirements.setMinPlantProduction(1);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlants(-1);
        player.changeMoneyProduction(4);
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
