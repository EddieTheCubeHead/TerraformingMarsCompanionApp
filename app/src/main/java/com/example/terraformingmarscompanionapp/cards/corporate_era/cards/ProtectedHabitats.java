package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.EffectCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class ProtectedHabitats extends Card {
    public ProtectedHabitats(Game game) {
        super(Type.BLUE, game);
        name = "Protected habitats";
        price = 5;
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.setOrganicsProtected(true);
        super.playWithMetadata(player, data);
    }
}
