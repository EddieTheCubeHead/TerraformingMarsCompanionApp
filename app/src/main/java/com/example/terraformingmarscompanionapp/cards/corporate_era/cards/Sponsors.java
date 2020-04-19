package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Sponsors extends Card {
    public Sponsors(Game game) {
        super(Type.GREEN);
        name = "Sponsors";
        price = 6;
        tags.add(Tag.EARTH);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        owner_player.changeMoneyProduction(2);
        return super.onPlay(player);
    }
}
