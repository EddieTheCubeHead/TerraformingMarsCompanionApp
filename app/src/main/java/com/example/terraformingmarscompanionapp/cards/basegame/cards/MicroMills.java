package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class MicroMills extends Card {
    public MicroMills(Game game) {
        super(Type.GREEN);
        name = "Micro-mills";
        price = 3;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeHeatProduction(1);
        return super.onPlay(player);
    }
}