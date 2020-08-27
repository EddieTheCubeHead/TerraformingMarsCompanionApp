package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class NitrophilicMoss extends Card {
    public NitrophilicMoss() {
        super(Type.GREEN);
        name = "Nitrophilic moss";
        price = 8;
        tags.add(Tag.PLANT);
        requirements.setMinOceans(3);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        player.getResources().setPlants(player.getResources().getPlants() - 2);
        production_box.setPlantsProduction(2);
        super.playWithMetadata(player, data);
    }
}
