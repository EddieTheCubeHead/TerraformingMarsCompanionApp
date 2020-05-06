package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class AdaptedLichen extends Card {
    public AdaptedLichen(Game game) {
        super(Type.GREEN, game);
        name = "Adapted lichen";
        price = 9;
        tags.add(Tag.PLANT);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setPlantsProduction(1);
        super.playWithMetadata(player, data);
    }
}
