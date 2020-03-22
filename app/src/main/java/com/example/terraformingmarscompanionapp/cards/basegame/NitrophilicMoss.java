package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class NitrophilicMoss extends Card {
    public NitrophilicMoss(Game game) {
        name = "Nitrophilic moss";
        price = 8;
        tags.put("plant", 1);
        requirements.put("min_oceans", 3);
        requirements.put("min_plants", 2);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlants(-2);
        player.changePlantsProduction(2);
        player.addPlantTag();
        player.addGreen(this);
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction(Player player) {
        return false;
    }
}
