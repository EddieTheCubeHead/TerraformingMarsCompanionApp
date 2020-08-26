package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Greenhouses extends Card {
    public Greenhouses() {
        super(Type.GREEN);
        name = "Greenhouses";
        price = 6;
        tags.add(Tag.BUILDING);
        tags.add(Tag.PLANT);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        Integer city_count = game.getCitiesInSpace() + game.getCitiesOnMars();
        player.getResources().setPlants(player.getResources().getPlants() + city_count);
        super.playWithMetadata(player, data);
    }
}
