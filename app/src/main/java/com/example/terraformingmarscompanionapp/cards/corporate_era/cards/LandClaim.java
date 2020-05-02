package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class LandClaim extends Card {
    public LandClaim(Game game) {
        super(Type.RED);
        name = "Asteroid mining consortium";
        price = 1;
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player)
    {
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        //TODO Hexan varaaminen
        super.playWithMetadata(player, data);
    }
}
