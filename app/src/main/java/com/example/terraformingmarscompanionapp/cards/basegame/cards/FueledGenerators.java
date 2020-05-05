package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class FueledGenerators extends Card {
    public FueledGenerators(Game game) {
        super(Type.GREEN, game);
        name = "Fueled generators";
        price = 1;
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeMoneyProduction(-1);
        player.changeEnergyProduction(1);
        super.playWithMetadata(player, data);
    }
}
