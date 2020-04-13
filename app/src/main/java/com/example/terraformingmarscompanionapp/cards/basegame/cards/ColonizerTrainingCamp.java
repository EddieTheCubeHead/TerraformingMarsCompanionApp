package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class ColonizerTrainingCamp extends Card {
    public ColonizerTrainingCamp(Game game) {
        super("green");
        name = "Colonizer training camp";
        price = 8;
        tags.add("jovian");
        tags.add("building");
        requirements.setMaxOxygen(5);
        victory_points = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
