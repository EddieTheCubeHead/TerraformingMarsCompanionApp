package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Ecoline extends Card {
    public Ecoline(Game game) {
        super(Type.CORPORATION, game);
        name = "Ecoline";
        tags.add(Tag.PLANT);
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getModifiers().setGreeneryPlantCostModifier(-1);
        production_box.setPlantsProduction(2);
        player.getResources().setPlants(3);
        player.getResources().setMoney(36);
        super.playWithMetadata(player, data);
    }
}
