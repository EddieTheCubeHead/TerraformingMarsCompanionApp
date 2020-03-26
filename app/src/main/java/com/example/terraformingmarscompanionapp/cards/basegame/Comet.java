package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Comet extends Card {
    public Comet(Game game) {
        name = "Comet";
        price = 21;
        tags.put("space", 1);
        tags.put("event", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.updateManager.onSpaceEvent(player);
        player.addRed(this);
        owner_game.raiseTemperature(player);
        owner_game.placeOcean(player, false);
        //TODO poista toiselta kolme kasvia UI
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
