package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class MassConverter extends Card {
    public MassConverter(Game game) {
        super(Type.BLUE);
        name = "Mass conventer";
        price = 8;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.ENERGY);
        requirements.setMinScienceTags(5);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeEnergyProduction(6);
        player.changeSpaceTagDiscount(2);
        return super.onPlay(player);
    }
}
