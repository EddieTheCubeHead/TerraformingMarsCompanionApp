package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class DesignedMicroorganisms extends Card {
    public DesignedMicroorganisms(Game game) {
        super("green");
        name = "Designed microorganisms";
        price = 16;
        tags.add("science");
        tags.add("microbe");
        requirements.put("max_temperature", -14);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlantsProduction(2);
        super.onPlay(player);
    }
}
