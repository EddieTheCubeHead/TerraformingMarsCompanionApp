package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class AdaptationTechnology extends Card {
    public AdaptationTechnology(Game game) {
        super("blue");
        name = "Adaptation technology";
        price = 12;
        tags.add("science");
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeBaseTrRequirementDiscount(2);
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
