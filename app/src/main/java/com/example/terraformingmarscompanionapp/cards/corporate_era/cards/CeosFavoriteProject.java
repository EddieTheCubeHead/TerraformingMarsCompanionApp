package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class CeosFavoriteProject extends Card {
    public CeosFavoriteProject(Game game) {
        super(Type.RED, game);
        name = "CEO's favorite project";
        price = 1;
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        //TODO Lisää 1 resurssi kortille jolla on jo 1 resurssi
        super.playWithMetadata(player, data);
    }
}
