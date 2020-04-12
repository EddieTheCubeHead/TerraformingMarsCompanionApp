package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Archaebacteria extends Card {
    public Archaebacteria(Game game) {
        super("green");
        name = "Archaebacteria";
        price = 6;
        tags.add("microbe");
        requirements.put("max_temperature", -18);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlantsProduction(1);
        super.onPlay(player);
    }
}
