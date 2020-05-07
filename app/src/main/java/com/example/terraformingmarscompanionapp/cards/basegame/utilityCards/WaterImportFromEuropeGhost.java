package com.example.terraformingmarscompanionapp.cards.basegame.utilityCards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class WaterImportFromEuropeGhost extends Card {
    public WaterImportFromEuropeGhost(Game game) {
        super(Type.OTHER, game);
        name = "Water import from europe ghost";
        price = 12;
        tags.add(Tag.SPACE);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        overridePlayActionCall();
        super.playWithMetadata(player, data);
    }
}
