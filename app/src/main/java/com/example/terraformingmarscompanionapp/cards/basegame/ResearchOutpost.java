package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class ResearchOutpost extends Card {
    public ResearchOutpost(Game game) {
        name = "Research outpost";
        price = 18;
        tags.put("science", 1);
        tags.put("building", 1);
        tags.put("city", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeCardDiscount(1);
        owner_game.placeCity(player, 1);
        player.addPassive(this);
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
