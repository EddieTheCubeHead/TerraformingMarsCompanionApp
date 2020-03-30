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
        player.addRed(this);
        owner_player = player;
        while (true) {
            if (owner_game.tile_handler.placeLavaFlow(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja virheenhallinta jos jokainen volcanic-tiili varattu
            }
        }
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
