package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class ProtectedValley extends Card {
    public ProtectedValley(Game game) {
        name = "Protected valley";
        price = 23;
        tags.put("plant", 1);
        tags.put("building", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addPlantTag();
        player.addBuildingTag();
        player.changeMoneyProduction(2);
        owner_game.placeForest(player, true);
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
