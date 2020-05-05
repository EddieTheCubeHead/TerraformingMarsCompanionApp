package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class MicroMills extends Card {
    public MicroMills(Game game) {
        super(Type.GREEN, game);
        name = "Micro-mills";
        price = 3;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeHeatProduction(1);
        super.playWithMetadata(player, data);
    }
}