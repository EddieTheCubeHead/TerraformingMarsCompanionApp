package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class MiningExpedition extends Card {
    public MiningExpedition(Game game) {
        super("red");
        name = "Mining expedition";
        price = 12;
        tags.add("event");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeSteel(2);
        owner_game.raiseOxygen(player);
        //TODO toiselta pelaajalta kasvien poisto
        super.onPlay(player);
    }
}
