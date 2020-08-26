package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class FoodFactory extends Card {
    public FoodFactory() {
        super(Type.GREEN);
        name = "Food factory";
        price = 12;
        tags.add(Tag.BUILDING);
        requirements.setMinPlantProduction(1);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setPlantsProduction(-1);
        production_box.setMoneyProduction(4);
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
