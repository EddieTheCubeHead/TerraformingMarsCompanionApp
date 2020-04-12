package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class DeepWellHeating extends Card {
    public DeepWellHeating(Game game) {
        super("green");
        name = "Deep well heating";
        price = 13;
        tags.add("energy");
        tags.add("building");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(1);
        owner_game.raiseTemperature(player);
        super.onPlay(player);
    }
}
