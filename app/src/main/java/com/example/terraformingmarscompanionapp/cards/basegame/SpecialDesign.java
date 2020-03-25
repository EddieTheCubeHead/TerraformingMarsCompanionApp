package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class SpecialDesign extends Card {
    public SpecialDesign(Game game) {
        name = "Special design";
        price = 4;
        tags.put("science", 1);
        tags.put("event", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addEventTag();
        player.addRed(this);
        player.setSpecialDesignEffect(true);
        player.changeBaseTrRequirementDiscount(2);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
