package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class ReleaseOfInertGases extends Card {
    public ReleaseOfInertGases(Game game) {
        super(Type.RED, game);
        name = "Release of inert gases";
        price = 14;
        tags.add(Tag.EVENT);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeTerraformingRating(2);
        super.playWithMetadata(player, data);
    }
}
