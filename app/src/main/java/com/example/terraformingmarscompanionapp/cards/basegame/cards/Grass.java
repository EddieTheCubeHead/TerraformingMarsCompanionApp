package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Grass extends Card {
    public Grass(Game game) {
        super(Type.GREEN);
        name = "Grass";
        price = 11;
        tags.add(Tag.PLANT);
        requirements.setMinTemperature(-16);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changePlantsProduction(1);
        player.changePlants(3);
        return super.onPlay(player);
    }
}
