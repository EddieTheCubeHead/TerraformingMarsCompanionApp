package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Satellites extends Card {
    public Satellites(Game game) {
        super(Type.GREEN, game);
        name = "Satellites";
        price = 10;
        tags.add(Tag.SPACE);
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        super.onPlay(player);
        player.changeMoneyProduction(player.getSpaceTags());
        super.playWithMetadata(player, data);
    }
}
