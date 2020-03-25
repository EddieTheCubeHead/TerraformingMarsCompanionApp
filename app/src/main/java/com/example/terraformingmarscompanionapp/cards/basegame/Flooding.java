package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Flooding extends Card {
    public Flooding(Game game) {
        name = "Flooding";
        price = 7;
        tags.put("event", 1);
        victory_points = -1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addEventTag();
        player.addRed(this);
        //TODO poista toiselta neljä rahaa UI
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
