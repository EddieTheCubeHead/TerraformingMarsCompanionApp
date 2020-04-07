package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Ecoline extends Card {
    public Ecoline(Game game) {
        name = "Ecoline";
        tags.put("plant", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeGreeneryPlantCostModifier(1);
        player.changePlantsProduction(2);
        player.changePlants(3);
        player.addPlantTag();
        player.changeMoney(36);
        owner_player = player;
        player.setCorporation(this);
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
