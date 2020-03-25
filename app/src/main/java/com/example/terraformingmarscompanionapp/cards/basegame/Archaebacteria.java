package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Archaebacteria extends Card {
    public Archaebacteria(Game game) {
        name = "Archaebacteria";
        price = 6;
        tags.put("microbe", 1);
        requirements.put("max_temperature", -18);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addMicrobeTag();
        player.changePlantsProduction(1);
        player.addGreen(this);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
