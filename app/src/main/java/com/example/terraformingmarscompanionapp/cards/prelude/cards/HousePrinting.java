package com.example.terraformingmarscompanionapp.cards.prelude.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class HousePrinting extends Card {
    public HousePrinting(Game game) {
        super(Type.GREEN);
        name = "House printing";
        price = 10;
        tags.add(Tag.BUILDING);
        victory_points = 1;
        owner_game = game;
    }


    @Override
    public void onPlay(Player player) {
        player.changeSteelProduction(1);
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
