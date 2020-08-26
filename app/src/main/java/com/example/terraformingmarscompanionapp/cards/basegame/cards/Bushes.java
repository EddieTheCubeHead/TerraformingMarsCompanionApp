package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Bushes extends Card {
    public Bushes() {
        super(Type.GREEN);
        name = "Bushes";
        price = 10;
        tags.add(Tag.PLANT);
        requirements.setMinTemperature(-10);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        production_box.setPlantsProduction(2);
        player.getResources().setPlants(player.getResources().getPlants() + 2);
        super.playWithMetadata(player, data);
    }
}
