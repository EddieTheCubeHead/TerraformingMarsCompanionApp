package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Greenhouses extends Card {
    public Greenhouses(Game game) {
        super(Type.GREEN, game);
        name = "Greenhouses";
        price = 6;
        tags.add(Tag.BUILDING);
        tags.add(Tag.PLANT);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        Integer city_count = owner_game.getCitiesInSpace() + owner_game.getCitiesOnMars();
        player.getResources().setPlants(player.getResources().getPlants() + city_count);
        super.playWithMetadata(player, data);
    }
}
