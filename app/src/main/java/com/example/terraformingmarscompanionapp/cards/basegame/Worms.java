package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Worms extends Card {
    public Worms(Game game) {
        name = "Worms";
        price = 8;
        tags.put("microbe", 1);
        requirements.put("min_oxygen", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addMicrobeTag();
        player.changePlantsProduction(player.getMicrobeTags()/2);
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
