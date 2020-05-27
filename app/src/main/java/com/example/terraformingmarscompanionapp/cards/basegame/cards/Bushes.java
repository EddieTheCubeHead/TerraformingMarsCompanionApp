package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Bushes extends Card {
    public Bushes(Game game) {
        super(Type.GREEN, game);
        name = "Bushes";
        price = 10;
        tags.add(Tag.PLANT);
        requirements.setMinTemperature(10);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setPlantsProduction(2);
        player.changePlants(2);
        super.playWithMetadata(player, data);
    }
}
