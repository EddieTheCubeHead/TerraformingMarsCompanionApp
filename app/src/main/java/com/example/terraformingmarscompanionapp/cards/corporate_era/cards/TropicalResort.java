package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class TropicalResort extends Card {
    public TropicalResort(Game game) {
        super(Type.GREEN);
        name = "Tropical resort";
        price = 13;
        tags.add(Tag.BUILDING);
        requirements.setMinHeatProduction(2);
        victory_points = 2;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeMoneyProduction(3);
        player.changeHeatProduction(-2);
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }
}
