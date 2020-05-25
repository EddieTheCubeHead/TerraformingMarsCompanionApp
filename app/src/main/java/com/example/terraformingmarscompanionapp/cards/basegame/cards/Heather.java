package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

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
        player.changePlants(1);
        production_box.setPlantsProduction(1);
        super.playWithMetadata(player, data);
    }
}
