package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class NuclearPower extends Card {
    public NuclearPower(Game game) {
        super(Type.GREEN, game);
        name = "Nuclear power";
        price = 10;
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeMoneyProduction(-2);
        player.changeEnergyProduction(3);
        super.playWithMetadata(player, data);
    }
}
