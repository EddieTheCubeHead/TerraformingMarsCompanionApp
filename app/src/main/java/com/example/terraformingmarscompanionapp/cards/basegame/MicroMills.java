package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

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