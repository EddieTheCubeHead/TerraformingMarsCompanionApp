package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class IndustrialMicrobes extends Card {
    public IndustrialMicrobes() {
        super(Type.GREEN);
        name = "Industrial microbes";
        price = 12;
        tags.add(Tag.MICROBE);
        tags.add(Tag.BUILDING);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        production_box.setSteelProduction(1);
        production_box.setEnergyProduction(1);
        super.playWithMetadata(player, data);
    }
}
