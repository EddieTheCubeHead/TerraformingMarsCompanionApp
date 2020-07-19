package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class ReleaseOfInertGases extends Card {
    public ReleaseOfInertGases(Game game) {
        super(Type.RED, game);
        name = "Release of inert gases";
        price = 14;
        tags.add(Tag.EVENT);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getResources().setTerraformingRating(player.getResources().getTerraformingRating() + 2);
        super.playWithMetadata(player, data);
    }
}
