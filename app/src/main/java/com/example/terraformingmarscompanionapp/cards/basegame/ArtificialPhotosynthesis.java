package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class ArtificialPhotosynthesis extends Card {
    public ArtificialPhotosynthesis(Game game) {
        super("green");
        name = "Artificial photosynthesis";
        price = 12;
        tags.add("science");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        boolean as_plant = true;
        //TODO kysely otetaanko kasveina vai energiana
        if (as_plant) {
            player.changePlantsProduction(1);
        } else {
            player.changeEnergyProduction(2);
        }
        super.onPlay(player);
    }
}
