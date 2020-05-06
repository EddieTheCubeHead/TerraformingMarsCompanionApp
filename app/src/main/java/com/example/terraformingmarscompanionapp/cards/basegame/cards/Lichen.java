package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Lichen extends Card {
    public Lichen(Game game) {
        super(Type.GREEN, game);
        name = "Lichen";
        price = 7;
        tags.add(Tag.PLANT);
        requirements.setMinTemperature(-24);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setPlantsProduction(1);
        super.playWithMetadata(player, data);
    }
}
