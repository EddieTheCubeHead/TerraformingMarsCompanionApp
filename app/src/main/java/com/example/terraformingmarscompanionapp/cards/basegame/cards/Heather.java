package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Heather extends Card {
    public Heather(Game game) {
        super(Type.GREEN, game);
        name = "Heather";
        price = 6;
        tags.add(Tag.PLANT);
        requirements.setMinTemperature(-14);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getResources().setPlants(player.getResources().getPlants() + 1);
        production_box.setPlantsProduction(1);
        super.playWithMetadata(player, data);
    }
}
