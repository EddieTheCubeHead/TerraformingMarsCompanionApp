package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

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
