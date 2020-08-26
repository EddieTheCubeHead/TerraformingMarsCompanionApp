package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class RadSuits extends Card {
    public RadSuits() {
        super(Type.GREEN);
        name = "Rad-suits";
        price = 6;
        requirements.setMinGlobalCities(2);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setMoneyProduction(1);
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
