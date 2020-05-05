package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Mine extends Card {
    public Mine(Game game) {
        super(Type.GREEN, game);
        name = "Mine";
        price = 4;
        tags.add(Tag.BUILDING);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeSteelProduction(1);
        super.playWithMetadata(player, data);
    }
}
