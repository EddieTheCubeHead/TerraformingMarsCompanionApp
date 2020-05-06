package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class IndustrialMicrobes extends Card {
    public IndustrialMicrobes(Game game) {
        super(Type.GREEN, game);
        name = "Industrial microbes";
        price = 12;
        tags.add(Tag.MICROBE);
        tags.add(Tag.BUILDING);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setSteelProduction(1);
        production_box.setEnergyProduction(1);
        super.playWithMetadata(player, data);
    }
}
