package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class BribedCommittee extends Card {
    public BribedCommittee(Game game) {
        super(Type.RED, game);
        name = "Bribed committee";
        price = 7;
        tags.add(Tag.EARTH);
        tags.add(Tag.EVENT);
        victory_points = -2;
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeTerraformingRating(2);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
