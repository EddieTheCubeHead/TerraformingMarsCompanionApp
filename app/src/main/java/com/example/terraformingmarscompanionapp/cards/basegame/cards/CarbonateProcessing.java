package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class CarbonateProcessing extends Card {
    public CarbonateProcessing(Game game) {
        super(Type.GREEN, game);
        name = "Carbonate processing";
        price = 6;
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyProduction(1);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setEnergyProduction(-1);
        production_box.setHeatProduction(3);
        super.playWithMetadata(player, data);
    }
}
