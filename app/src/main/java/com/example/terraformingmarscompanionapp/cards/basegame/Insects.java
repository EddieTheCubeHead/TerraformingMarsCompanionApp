package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Insects extends Card {
    public Insects(Game game) {
        name = "Insects";
        price = 9;
        tags.put("microbe", 1);
        requirements.put("min_oxygen", 6);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addMicrobeTag();
        player.changePlantsProduction(player.getPlantTags());
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
