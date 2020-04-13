package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class GreatDam extends Card {
    public GreatDam(Game game) {
        super("green");
        name = "Great dam";
        price = 12;
        tags.add("energy");
        tags.add("building");
        requirements.setMinOceans(4);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(2);
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
