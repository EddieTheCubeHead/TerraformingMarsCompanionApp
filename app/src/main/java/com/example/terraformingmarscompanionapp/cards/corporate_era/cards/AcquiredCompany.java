package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class AcquiredCompany extends Card {
    public AcquiredCompany(Game game) {
        super(Type.GREEN, game);
        name = "Acquired company";
        price = 10;
        tags.add(Tag.EARTH);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_player.changeMoneyProduction(3);
        super.playWithMetadata(player, data);
    }
}
