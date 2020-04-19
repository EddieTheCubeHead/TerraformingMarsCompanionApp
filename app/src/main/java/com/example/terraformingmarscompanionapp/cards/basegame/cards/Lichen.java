package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Lichen extends Card {
    public Lichen(Game game) {
        super(Type.GREEN);
        name = "Lichen";
        price = 7;
        tags.add(Tag.PLANT);
        requirements.setMinTemperature(-24);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changePlantsProduction(1);
        return super.onPlay(player);
    }
}
