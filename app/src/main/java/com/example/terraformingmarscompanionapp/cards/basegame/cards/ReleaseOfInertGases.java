package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class ReleaseOfInertGases extends Card {
    public ReleaseOfInertGases(Game game) {
        super("red");
        name = "Release of inert gases";
        price = 14;
        tags.add("event");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeTerraformingRating(2);
        super.onPlay(player);
    }
}
