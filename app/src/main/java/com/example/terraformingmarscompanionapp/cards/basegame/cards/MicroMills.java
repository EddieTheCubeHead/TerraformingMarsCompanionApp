package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class MicroMills extends Card {
    public MicroMills(Game game) {
        super("green");
        name = "Micro-mills";
        price = 3;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeHeatProduction(1);
        super.onPlay(player);
    }
}