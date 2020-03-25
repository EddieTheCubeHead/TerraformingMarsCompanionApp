package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class EonChasmaNationalPark extends Card {
    public EonChasmaNationalPark(Game game) {
        name = "Eon chasma national park";
        price = 16;
        tags.put("plant", 1);
        tags.put("building", 1);
        requirements.put("min_temperature", -12);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addPlantTag();
        player.addBuildingTag();
        player.changePlants(3);
        player.changeMoneyProduction(2);
        //TODO lisää eläin toiselle kortille
        player.addGreen(this);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
