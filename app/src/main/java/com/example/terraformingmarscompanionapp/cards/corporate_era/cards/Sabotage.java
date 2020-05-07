package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Sabotage extends Card {
    public Sabotage(Game game) {
        super(Type.GREEN, game);
        name = "Sabotage";
        price = 1;
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        //TODO vähennä joltain pelaajalta 3 titaniumia 4 Rautaa tai 7 rahaa
        super.playWithMetadata(player, data);
    }
}
