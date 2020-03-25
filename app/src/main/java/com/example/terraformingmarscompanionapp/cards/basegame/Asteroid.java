package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Asteroid extends Card {
    public Asteroid(Game game) {
        name = "Asteroid";
        price = 14;
        tags.put("space", 1);
        tags.put("event", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.updateManager.onSpaceEvent(player);
        owner_game.raiseTemperature(player);
        player.changeTitanium(2);
        //TODO poista toiselta 3 kasvia
        owner_player = player;
        player.addRed(this);
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
