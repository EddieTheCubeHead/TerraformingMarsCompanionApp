package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class PermafrostExtraction extends Card {
    public PermafrostExtraction(Game game) {
        name = "Permafrost extraction";
        price = 8;
        tags.put("event", 1);
        requirements.put("min_temperature", -8);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addEventTag();
        owner_game.placeOcean(player, false);
        player.addRed(this);
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
