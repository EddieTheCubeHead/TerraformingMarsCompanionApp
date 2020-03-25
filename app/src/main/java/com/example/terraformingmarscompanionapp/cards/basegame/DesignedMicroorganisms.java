package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class DesignedMicroorganisms extends Card {
    public DesignedMicroorganisms(Game game) {
        name = "Designed microorganisms";
        price = 16;
        tags.put("science", 1);
        tags.put("microbe", 1);
        requirements.put("max_temperature", -14);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addScienceTag();
        player.addMicrobeTag();
        player.changePlantsProduction(2);
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
