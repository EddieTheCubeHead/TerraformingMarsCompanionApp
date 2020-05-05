package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Moss extends Card {

    public Moss(Game game) {
        super(Type.GREEN, game);
        name = "Moss";
        price = 4;
        tags.add(Tag.PLANT);
        requirements.setMinOceans(3);
        requirements.setMinPlants(1);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changePlants(-1);
        player.changePlantsProduction(1);
        super.playWithMetadata(player, data);
    }
}
