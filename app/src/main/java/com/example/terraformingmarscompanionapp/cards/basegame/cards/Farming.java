package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Farming extends Card {
    public Farming(Game game) {
        super(Type.GREEN, game);
        name = "Farming";
        price = 16;
        tags.add(Tag.PLANT);
        requirements.setMinTemperature(4);
        victory_points = 2;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changePlants(2);
        production_box.setPlantsProduction(2);
        production_box.setMoneyProduction(2);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
