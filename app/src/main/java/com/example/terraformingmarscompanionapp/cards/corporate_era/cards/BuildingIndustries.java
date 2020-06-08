package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class BuildingIndustries extends Card {
    public BuildingIndustries(Game game) {
        super(Type.GREEN, game);
        name = "Building industries";
        price = 6;
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyProduction(1);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setSteelProduction(2);
        production_box.setEnergyProduction(-1);
        super.playWithMetadata(player, data);
    }
}
