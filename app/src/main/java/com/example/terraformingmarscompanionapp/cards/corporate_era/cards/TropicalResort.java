package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class TropicalResort extends Card {
    public TropicalResort(Game game) {
        super(Type.GREEN, game);
        name = "Tropical resort";
        price = 13;
        tags.add(Tag.BUILDING);
        requirements.setMinHeatProduction(2);
        victory_points = 2;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setMoneyProduction(3);
        production_box.setHeatProduction(-2);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
