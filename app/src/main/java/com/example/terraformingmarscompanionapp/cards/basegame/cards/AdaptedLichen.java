package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class AdaptedLichen extends Card {
    public AdaptedLichen(Game game) {
        super(Type.GREEN);
        name = "Adapted lichen";
        price = 9;
        tags.add(Tag.PLANT);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlantsProduction(1);
        super.onPlay(player);
    }
}
