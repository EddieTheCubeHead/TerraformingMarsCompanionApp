package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class ArtificialPhotosynthesis extends Card {
    public ArtificialPhotosynthesis(Game game) {
        super(Type.GREEN);
        name = "Artificial photosynthesis";
        price = 12;
        tags.add(Tag.SCIENCE);
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
