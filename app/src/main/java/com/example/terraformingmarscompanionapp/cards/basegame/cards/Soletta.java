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
    public Integer onPlay(Player player) {
        player.changeHeatProduction(7);
        return super.onPlay(player);
    }
}
