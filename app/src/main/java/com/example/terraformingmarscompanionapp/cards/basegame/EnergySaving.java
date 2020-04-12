package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class EnergySaving extends Card {
    public EnergySaving(Game game) {
        super("green");
        name = "Energy saving";
        price = 15;
        tags.add("energy");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(owner_game.getCitiesOnMars() + owner_game.getCitiesInSpace());
        super.onPlay(player);
    }
}
