package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class FusionPower extends Card {
    public FusionPower(Game game) {
        super(Type.GREEN, game);
        name = "Fusion power";
        price = 14;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyTags(2);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeEnergyProduction(3);
        super.playWithMetadata(player, data);
    }
}
