package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

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
