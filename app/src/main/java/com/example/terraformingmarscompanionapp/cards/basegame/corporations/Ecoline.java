package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class Ecoline extends Card {
    public Ecoline(Game game) {
        super("corporation");
        name = "Ecoline";
        tags.add("plant");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeGreeneryPlantCostModifier(1);
        player.changePlantsProduction(2);
        player.changePlants(3);
        player.changeMoney(36);
        super.onPlay(player);
    }
}
