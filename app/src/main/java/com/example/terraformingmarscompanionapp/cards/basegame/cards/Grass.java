package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Grass extends Card {
    public Grass() {
        super(Type.GREEN);
        name = "Grass";
        price = 11;
        tags.add(Tag.PLANT);
        requirements.setMinTemperature(-16);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setPlantsProduction(1);
        player.getResources().setPlants(player.getResources().getPlants() + 3);
        super.playWithMetadata(player, data);
    }
}
