package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class MiningExpedition extends Card {
    public MiningExpedition(Game game) {
        name = "Mining expedition";
        price = 12;
        tags.put("event", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addEventTag();
        player.changeSteel(2);
        player.addRed(this);
        owner_game.raiseOxygen(player);
        //TODO toiselta pelaajalta kasvien poisto
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
