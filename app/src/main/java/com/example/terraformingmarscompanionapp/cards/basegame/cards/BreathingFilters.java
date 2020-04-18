package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class BreathingFilters extends Card {
    public BreathingFilters(Game game) {
        super(Type.GREEN);
        name = "Breathing filters";
        price = 11;
        tags.add(Tag.SCIENCE);
        requirements.setMinOxygen(7);
        victory_points = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
