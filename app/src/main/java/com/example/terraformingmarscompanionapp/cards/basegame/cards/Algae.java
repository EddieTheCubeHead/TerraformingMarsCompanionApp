package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Algae extends Card {
    public Algae(Game game) {
        super(Type.GREEN, game);
        name = "Algae";
        price = 10;
        tags.add(Tag.PLANT);
        requirements.setMinOceans(5);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setPlantsProduction(2);
        player.getResources().setPlants(player.getResources().getPlants() + 1);
        super.playWithMetadata(player, data);
    }
}
