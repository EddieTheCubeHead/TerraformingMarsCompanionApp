package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Soletta extends Card {
    public Soletta(Game game) {
        super(Type.GREEN);
        name = "Soletta";
        price = 35;
        tags.add(Tag.SPACE);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeHeatProduction(7);
        super.onPlay(player);
    }
}
