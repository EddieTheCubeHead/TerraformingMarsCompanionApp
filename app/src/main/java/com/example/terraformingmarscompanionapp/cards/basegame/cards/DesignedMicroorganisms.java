package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

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
