package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class NitrophilicMoss extends Card {
    public NitrophilicMoss(Game game) {
        super(Type.GREEN);
        name = "Nitrophilic moss";
        price = 8;
        tags.add(Tag.PLANT);
        requirements.setMinOceans(3);
        requirements.setMinPlants(2);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlants(-2);
        player.changePlantsProduction(2);
        super.onPlay(player);
    }
}
