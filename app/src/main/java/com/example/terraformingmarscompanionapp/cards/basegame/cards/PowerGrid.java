package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class PowerGrid extends Card {
    public PowerGrid() {
        super(Type.GREEN);
        name = "Power grid";
        price = 18;
        tags.add(Tag.ENERGY);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        production_box.setEnergyProduction(player.getTags().getEnergyTags());
        super.playWithMetadata(player, data);
    }

    @Override
    public void playProductionBox() throws InvalidResourcesException {
        production_box.setEnergyProduction(owner_player.getTags().getEnergyTags());
        super.playProductionBox();
    }
}
