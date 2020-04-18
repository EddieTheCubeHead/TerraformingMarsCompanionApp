package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class SpecialDesign extends Card {
    public SpecialDesign(Game game) {
        super(Type.RED);
        name = "Special design";
        price = 4;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.setSpecialDesignEffect(true);
        player.changeBaseTrRequirementDiscount(2);
        super.onPlay(player);
    }
}
