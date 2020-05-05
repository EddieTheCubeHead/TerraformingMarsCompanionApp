package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class TollStation extends Card {
    public TollStation(Game game) {
        super(Type.GREEN, game);
        name = "Toll station";
        price = 12;
        tags.add(Tag.SPACE);
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        Integer tag_amount = 0;
        for (Player opponent : owner_game.getPlayers()) {
            if (opponent == player) {
                continue;
            }
            tag_amount += opponent.getSpaceTags();
        }
        player.changeMoneyProduction(tag_amount);
        super.playWithMetadata(player, data);
    }
}
