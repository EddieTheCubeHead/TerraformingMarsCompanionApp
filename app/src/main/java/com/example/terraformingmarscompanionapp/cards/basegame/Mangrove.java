package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Mangrove extends Card {
    public Mangrove(Game game) {
        name = "Mangrove";
        price = 12;
        tags.put("plant", 1);
        requirements.put("min_temperature", 4);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addPlantTag();
        owner_game.placeForest(player, true);
        player.addGreen(this);
        owner_player = player;
        owner_game.updateManager.onVpCardPlayed(player);
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
