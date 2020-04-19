package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Algae extends Card {
    public Algae(Game game) {
        super(Type.GREEN);
        name = "Algae";
        price = 10;
        tags.add(Tag.PLANT);
        requirements.setMinOceans(5);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changePlantsProduction(2);
        player.changePlants(1);
        return super.onPlay(player);
    }
}
