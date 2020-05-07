package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class HiredRaiders extends Card {
    public HiredRaiders(Game game) {
        super(Type.RED, game);
        name = "Hired raiders";
        price = 1;
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        //TODO varasta toiselta 2 rautaa tai 3 rahaa
        super.playWithMetadata(player, data);
    }
}
