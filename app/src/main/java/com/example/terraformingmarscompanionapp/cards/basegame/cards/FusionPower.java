package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class FusionPower extends Card {
    public FusionPower(Game game) {
        super(Type.GREEN);
        name = "Fusion power";
        price = 14;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyTags(2);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(3);
        super.onPlay(player);
    }
}
