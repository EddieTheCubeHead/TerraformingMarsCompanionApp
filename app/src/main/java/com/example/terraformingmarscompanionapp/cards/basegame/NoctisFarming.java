package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class NoctisFarming extends Card {
    public NoctisFarming(Game game) {
        name = "Noctis farming";
        price = 10;
        tags.put("plant", 1);
        tags.put("building", 1);
        requirements.put("min_temperature", -20);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addPlantTag();
        player.addBuildingTag();
        player.changeMoneyProduction(1);
        player.changePlants(2);
        player.addGreen(this);
        owner_game.updateManager.onVpCardPlayed(player);
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
