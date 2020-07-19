package com.example.terraformingmarscompanionapp.cards.prelude.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class HousePrinting extends Card {
    public HousePrinting(Game game) {
        super(Type.GREEN, game);
        name = "House printing";
        price = 10;
        tags.add(Tag.BUILDING);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getResources().setSteelProduction(player.getResources().getSteelProduction() + 1);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
