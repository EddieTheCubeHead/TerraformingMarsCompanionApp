package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class IndenturnedWorkers extends Card {
    public IndenturnedWorkers() {
        super(Type.RED);
        name = "Indenturned workers";
        price = 0;
        tags.add(Tag.EVENT);
        victory_points = -1;
    }



    @Override
    public void playWithMetadata(Player player, Integer data) {
        game.update_manager.onVpCardPlayed(player);

        player.getModifiers().setNextCardDiscount(8);
        super.playWithMetadata(player, data);
    }
}
