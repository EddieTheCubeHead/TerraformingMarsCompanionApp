package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Insects extends Card {
    public Insects(Game game) {
        super("green");
        name = "Insects";
        price = 9;
        tags.add("microbe");
        requirements.put("min_oxygen", 6);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlantsProduction(player.getPlantTags());
        super.onPlay(player);
    }
}
