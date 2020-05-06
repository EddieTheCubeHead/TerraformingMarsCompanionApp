package com.example.terraformingmarscompanionapp.cards.colonies.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class SolarReflectors extends Card {
    public SolarReflectors(Game game) {
        super(Type.GREEN, game);
        name = "Solar reflectors";
        price = 23;
        tags.add(Tag.SPACE);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setHeatProduction(5);
        super.playWithMetadata(player, data);
    }
}
