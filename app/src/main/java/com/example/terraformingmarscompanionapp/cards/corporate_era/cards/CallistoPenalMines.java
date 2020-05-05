package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class CallistoPenalMines extends Card {
    public CallistoPenalMines(Game game) {
        super(Type.GREEN, game);
        name = "Callisto penal mines";
        price = 24;
        tags.add(Tag.SPACE);
        tags.add(Tag.JOVIAN);
        victory_points = 2;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_player.changeMoneyProduction(3);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
