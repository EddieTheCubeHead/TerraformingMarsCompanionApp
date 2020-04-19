package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Ecoline extends Card {
    public Ecoline(Game game) {
        super(Type.CORPORATION);
        name = "Ecoline";
        tags.add(Tag.PLANT);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeGreeneryPlantCostModifier(1);
        player.changePlantsProduction(2);
        player.changePlants(3);
        player.changeMoney(36);
        return super.onPlay(player);
    }
}
