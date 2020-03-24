package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class EnergySaving extends Card {
    public EnergySaving(Game game) {
        name = "Energy saving";
        price = 15;
        tags.put("energy", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addEnergyTag();
        player.changeEnergyProduction(owner_game.getCitiesOnMars() + owner_game.getCitiesInSpace());
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
