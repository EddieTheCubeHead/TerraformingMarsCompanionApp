package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class BuildingIndustries extends Card {
    public BuildingIndustries(Game game) {
        super(Type.GREEN);
        name = "Building industries";
        price = 6;
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyProduction(1);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeSteelProduction(2);
        player.changeEnergyProduction(-1);
        return super.onPlay(player);
    }
}
