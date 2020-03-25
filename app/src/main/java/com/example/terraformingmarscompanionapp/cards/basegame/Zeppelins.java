package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Zeppelins extends Card {
    public Zeppelins(Game game) {
        name = "Zeppelins";
        price = 13;
        requirements.put("min_oxygen", 5);
        victory_points = 1;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.updateManager.onVpCardPlayed(player);
        player.addNullTag();
        player.changeMoneyProduction(owner_game.getCitiesOnMars());
        player.addGreen(this);
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
