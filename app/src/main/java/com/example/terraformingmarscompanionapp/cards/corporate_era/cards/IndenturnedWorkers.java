package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class IndenturnedWorkers extends Card {
    public IndenturnedWorkers(Game game) {
        super(Type.RED, game);
        name = "Indenturned workers";
        price = 0;
        tags.add(Tag.EVENT);
        victory_points = -1;
    }



    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        player.setNextCardDiscount(8);
        super.playWithMetadata(player, data);
    }
}
