package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Zeppelins extends Card {
    public Zeppelins(Game game) {
        super("green");
        name = "Zeppelins";
        price = 13;
        requirements.put("min_oxygen", 5);
        victory_points = 1;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        player.changeMoneyProduction(owner_game.getCitiesOnMars());
        super.onPlay(player);
    }
}
