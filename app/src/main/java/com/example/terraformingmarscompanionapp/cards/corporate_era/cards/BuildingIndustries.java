package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class BuildingIndustries extends Card {
    public BuildingIndustries() {
        super(Type.GREEN);
        name = "Building industries";
        price = 6;
        tags.add(Tag.BUILDING);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        production_box.setSteelProduction(2);
        production_box.setEnergyProduction(-1);
        super.playWithMetadata(player, data);
    }
}
