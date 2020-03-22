package com.example.terraformingmarscompanionapp.cards.basegame;

import android.util.Log;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Moss extends Card {

    public Moss(Game game) {
        name = "Moss";
        price = 4;
        tags.put("plant", 1);
        requirements.put("min_oceans", 3);
        requirements.put("min_plants", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_player = player;
        if (!player.changePlants(-1)) {
            System.out.println("Virhe kortin vaatimusten tarkastuksessa.");
        }
        player.changePlantsProduction(1);
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
