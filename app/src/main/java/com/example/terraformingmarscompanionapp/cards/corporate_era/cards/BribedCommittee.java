package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
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
        player.getResources().setTerraformingRating(player.getResources().getTerraformingRating() + 2);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
