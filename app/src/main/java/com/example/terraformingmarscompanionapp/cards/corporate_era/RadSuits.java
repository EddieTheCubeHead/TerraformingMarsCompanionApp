package com.example.terraformingmarscompanionapp.cards.corporate_era;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class RadSuits extends Card {
    public RadSuits(Game game) {
        super("green");
        name = "Rad-suits";
        price = 6;
        requirements.put("min_personal_cities", 2);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeMoneyProduction(1);
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
