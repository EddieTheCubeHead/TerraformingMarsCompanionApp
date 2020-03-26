package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class NuclearZone extends Card {
    public NuclearZone(Game game) {
        name = "Nuclear zone";
        price = 10;
        tags.put("earth", 1);
        victory_points = -2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addEarthTag();
        owner_game.raiseTemperature(player);
        owner_game.raiseTemperature(player);
        //TODO lisää laatan asettaminen
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
