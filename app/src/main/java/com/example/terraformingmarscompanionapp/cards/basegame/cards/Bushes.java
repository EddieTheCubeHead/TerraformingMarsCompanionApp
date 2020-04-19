package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Bushes extends Card {
    public Bushes(Game game) {
        super(Type.GREEN);
        name = "Bushes";
        price = 10;
        tags.add(Tag.PLANT);
        requirements.setMinTemperature(10);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changePlantsProduction(2);
        player.changePlants(2);
        return super.onPlay(player);
    }
}
