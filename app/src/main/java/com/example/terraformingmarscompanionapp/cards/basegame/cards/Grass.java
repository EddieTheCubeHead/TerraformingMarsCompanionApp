package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Grass extends Card {
    public Grass(Game game) {
        super(Type.GREEN, game);
        name = "Grass";
        price = 11;
        tags.add(Tag.PLANT);
        requirements.setMinTemperature(-16);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setPlantsProduction(1);
        player.changePlants(3);
        super.playWithMetadata(player, data);
    }
}
