package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class TransNeptuneProbe extends Card {
    public TransNeptuneProbe() {
        super(Type.GREEN);
        name = "Trans-neptune probe";
        price = 6;
        tags.add(Tag.SPACE);
        tags.add(Tag.SCIENCE);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
