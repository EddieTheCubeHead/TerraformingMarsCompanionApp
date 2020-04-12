package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class NoctisFarming extends Card {
    public NoctisFarming(Game game) {
        super("green");
        name = "Noctis farming";
        price = 10;
        tags.add("plant");
        tags.add("building");
        requirements.put("min_temperature", -20);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeMoneyProduction(1);
        player.changePlants(2);
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
