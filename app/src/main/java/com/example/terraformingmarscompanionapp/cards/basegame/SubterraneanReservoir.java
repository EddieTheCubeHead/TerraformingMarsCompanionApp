package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class SubterraneanReservoir extends Card {
    public SubterraneanReservoir(Game game) {
        name = "Subterranean reservoir";
        price = 11;
        tags.put("event", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addEventTag();
        player.addRed(this);
        owner_game.placeOcean(player, false);
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
