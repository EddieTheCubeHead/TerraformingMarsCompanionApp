package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Farming extends Card {
    public Farming() {
        super(Type.GREEN);
        name = "Farming";
        price = 16;
        tags.add(Tag.PLANT);
        requirements.setMinTemperature(4);
        victory_points = 2;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        player.getResources().setPlants(player.getResources().getPlants() + 2);
        production_box.setPlantsProduction(2);
        production_box.setMoneyProduction(2);
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
