package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Windmills extends Card {
    public Windmills(Game game) {
        super("green");
        name = "Windmills";
        price = 6;
        tags.add("energy");
        tags.add("building");
        requirements.put("min_oxygen", 1);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeSteelProduction(1);
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
