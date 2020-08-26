package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class GHGFactories extends Card {
    public GHGFactories() {
        super(Type.GREEN);
        name = "GHG factories";
        price = 11;
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyProduction(1);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        production_box.setEnergyProduction(-1);
        production_box.setHeatProduction(4);
        super.playWithMetadata(player, data);
    }
}
