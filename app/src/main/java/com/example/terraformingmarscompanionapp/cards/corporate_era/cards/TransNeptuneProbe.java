package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class TransNeptuneProbe extends Card {
    public TransNeptuneProbe(Game game) {
        super(Type.GREEN);
        name = "Trans-neptune probe";
        price = 6;
        tags.add(Tag.SPACE);
        tags.add(Tag.SCIENCE);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }
}
