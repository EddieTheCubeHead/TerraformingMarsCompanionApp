package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Ecoline extends Card {
    public Ecoline(Game game) {
        super(Type.CORPORATION, game);
        name = "Ecoline";
        tags.add(Tag.PLANT);
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeGreeneryPlantCostModifier(1);
        player.changePlantsProduction(2);
        player.changePlants(3);
        player.changeMoney(36);
        super.playWithMetadata(player, data);
    }
}
