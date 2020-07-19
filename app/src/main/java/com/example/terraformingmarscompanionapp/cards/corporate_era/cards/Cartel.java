package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Cartel extends Card {
    public Cartel(Game game) {
        super(Type.GREEN, game);
        name = "Cartel";
        price = 8;
        tags.add(Tag.EARTH);
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getResources().setMoneyProduction(player.getResources().getMoneyProduction() + player.getTags().getEarthTags());
        super.playWithMetadata(player, data);
    }
}
