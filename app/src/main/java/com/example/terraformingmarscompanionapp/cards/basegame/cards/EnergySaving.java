package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class EnergySaving extends Card {
    public EnergySaving(Game game) {
        super(Type.GREEN, game);
        name = "Energy saving";
        price = 15;
        tags.add(Tag.ENERGY);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setEnergyProduction(owner_game.getCitiesOnMars() + owner_game.getCitiesInSpace());
        super.playWithMetadata(player, data);
    }

    @Override
    public void playProductionBox() {
        production_box.setEnergyProduction(owner_game.getCitiesOnMars() + owner_game.getCitiesInSpace());
        super.playProductionBox();
    }
}
