package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class LavaFlows extends Card {
    public LavaFlows(Game game) {
        name = "Lava flows";
        price = 18;
        tags.put("event", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addEventTag();
        owner_game.raiseTemperature(player);
        owner_game.raiseTemperature(player);
        //TODO lisää laatan asettaminen
        player.addRed(this);
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
