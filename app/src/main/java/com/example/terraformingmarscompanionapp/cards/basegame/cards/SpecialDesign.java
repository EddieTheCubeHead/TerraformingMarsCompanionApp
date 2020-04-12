package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class SpecialDesign extends Card {
    public SpecialDesign(Game game) {
        super("red");
        name = "Special design";
        price = 4;
        tags.add("science");
        tags.add("event");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.setSpecialDesignEffect(true);
        player.changeBaseTrRequirementDiscount(2);
        super.onPlay(player);
    }
}
