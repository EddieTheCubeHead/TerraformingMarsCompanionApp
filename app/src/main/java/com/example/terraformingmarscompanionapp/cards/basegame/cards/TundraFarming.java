package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class TundraFarming extends Card {
    public TundraFarming() {
        super(Type.GREEN);
        name = "Tundra farming";
        price = 16;
        tags.add(Tag.PLANT);
        requirements.setMinTemperature(-6);
        victory_points = 2;
        Card.game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getResources().setPlants(player.getResources().getPlants() + 1);
        production_box.setPlantsProduction(1);
        production_box.setMoneyProduction(2);
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
