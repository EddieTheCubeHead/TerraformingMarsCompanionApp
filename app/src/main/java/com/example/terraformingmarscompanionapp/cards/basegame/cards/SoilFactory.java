package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class SoilFactory extends Card {
    public SoilFactory() {
        super(Type.GREEN);
        name = "Soil factory";
        price = 9;
        tags.add(Tag.BUILDING);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        production_box.setEnergyProduction(-1);
        production_box.setPlantsProduction(1);
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
