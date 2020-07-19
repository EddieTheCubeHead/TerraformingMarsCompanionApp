package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class ProtectedHabitats extends Card {
    public ProtectedHabitats(Game game) {
        super(Type.BLUE, game);
        name = "Protected habitats";
        price = 5;
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getModifiers().setOrganicsProtected(true);
        super.playWithMetadata(player, data);
    }
}
