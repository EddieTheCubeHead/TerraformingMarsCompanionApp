package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Farming extends Card {
    public Farming(Game game) {
        super(Type.GREEN);
        name = "Farming";
        price = 16;
        tags.add(Tag.PLANT);
        requirements.setMinTemperature(4);
        victory_points = 2;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changePlants(2);
        player.changePlantsProduction(2);
        player.changeMoneyProduction(2);
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }
}
