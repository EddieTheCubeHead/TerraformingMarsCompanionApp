package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

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
