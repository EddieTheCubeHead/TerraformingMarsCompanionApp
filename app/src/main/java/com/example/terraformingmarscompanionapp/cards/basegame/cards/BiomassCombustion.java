package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

public final class BiomassCombustion extends Card {
    public BiomassCombustion(Game game) {
        super(Type.GREEN, game);
        name = "Biomass combustion";
        price = 4;
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
        requirements.setMinOxygen(6);
        victory_points = -1;
    }

    @Override
    public void onPlay(Player player) {
        //TODO pelaajan valinta UI
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data != 0) {
            GameController.getInstance().getPlayer(data).takePlantsProduction(1);
        }
        player.changeEnergyProduction(2);
        super.onPlay(player);
    }
}
