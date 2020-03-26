package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class TowingAComet extends Card {
    public TowingAComet(Game game) {
        name = "Towing a comet";
        price = 23;
        tags.put("space", 1);
        tags.put("event", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.updateManager.onSpaceEvent(player);
        player.addEventTag();
        player.changePlants(2);
        owner_game.raiseOxygen(player);
        owner_game.placeOcean(player, false);
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
