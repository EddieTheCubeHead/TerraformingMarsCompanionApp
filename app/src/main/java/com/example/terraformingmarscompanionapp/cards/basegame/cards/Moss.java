package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Moss extends Card {

    public Moss() {
        super(Type.GREEN);
        name = "Moss";
        price = 4;
        tags.add(Tag.PLANT);
        requirements.setMinOceans(3);
        requirements.setMinPlants(1);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getResources().setPlants(player.getResources().getPlants() - 1);
        production_box.setPlantsProduction(1);
        super.playWithMetadata(player, data);
    }
}
