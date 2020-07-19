package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class MicroMills extends Card {
    public MicroMills(Game game) {
        super(Type.GREEN, game);
        name = "Micro-mills";
        price = 3;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setHeatProduction(1);
        super.playWithMetadata(player, data);
    }
}