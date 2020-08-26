package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Trees extends Card {
    public Trees() {
        super(Type.GREEN);
        name = "Trees";
        price = 13;
        tags.add(Tag.PLANT);
        requirements.setMinTemperature(-4);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setPlantsProduction(3);
        player.getResources().setPlants(player.getResources().getPlants() + 1);
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
