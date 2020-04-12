package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class BreathingFilters extends Card {
    public BreathingFilters(Game game) {
        super("green");
        name = "Breathing filters";
        price = 11;
        tags.add("science");
        requirements.put("min_oxygen", 7);
        victory_points = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
