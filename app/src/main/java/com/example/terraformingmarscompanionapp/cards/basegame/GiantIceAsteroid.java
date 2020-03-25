package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class GiantIceAsteroid extends Card {
    public GiantIceAsteroid(Game game) {
        name = "Giant ice asteroid";
        price = 36;
        tags.put("space", 1);
        tags.put("event", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.updateManager.onSpaceEvent(player);
        owner_game.placeOcean(player, false);
        owner_game.placeOcean(player, false);
        owner_game.raiseTemperature(player);
        owner_game.raiseTemperature(player);
        //TODO vähennä 6 kasvia muulta pelaajalta
        player.addEventTag();
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
