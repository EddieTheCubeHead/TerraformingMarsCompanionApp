package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class EonChasmaNationalPark extends Card {
    public EonChasmaNationalPark(Game game) {
        super(Type.GREEN);
        name = "Eon chasma national park";
        price = 16;
        tags.add(Tag.PLANT);
        tags.add(Tag.BUILDING);
        requirements.setMinTemperature(-12);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changePlants(3);
        player.changeMoneyProduction(2);
        //TODO lisää eläin toiselle kortille
        return super.onPlay(player);
    }
}
