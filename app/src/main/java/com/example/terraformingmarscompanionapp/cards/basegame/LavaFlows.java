package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class LavaFlows extends Card {
    public LavaFlows(Game game) {
        super("red");
        name = "Lava flows";
        price = 18;
        tags.add("event");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeLavaFlow(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja virheenhallinta jos jokainen volcanic-tiili varattu
            }
        }
        owner_game.raiseTemperature(player);
        owner_game.raiseTemperature(player);
        super.onPlay(player);
    }
}
