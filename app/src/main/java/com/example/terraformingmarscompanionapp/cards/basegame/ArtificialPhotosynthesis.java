package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class ArtificialPhotosynthesis extends Card {
    public ArtificialPhotosynthesis(Game game) {
        name = "Artificial photosynthesis";
        price = 12;
        tags.put("science", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addScienceTag();
        boolean as_plant = true;
        //TODO kysely otetaanko kasveina vai energiana
        if (as_plant) {
            player.changePlantsProduction(1);
        } else {
            player.changeEnergyProduction(2);
        }
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
