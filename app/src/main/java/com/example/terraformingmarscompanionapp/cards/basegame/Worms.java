package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Worms extends Card {
    public Worms(Game game) {
        super("green");
        name = "Worms";
        price = 8;
        tags.add("microbe");
        requirements.put("min_oxygen", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlantsProduction(player.getMicrobeTags()/2);
        super.onPlay(player);
    }
}
