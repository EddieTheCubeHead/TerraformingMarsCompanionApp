package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

public final class HeatTrappers extends Card {
    public HeatTrappers(Game game) {
        super(Type.GREEN, game);
        name = "Heat trappers";
        price = 6;
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
        victory_points = -1;
    }

    @Override
    public void onPlay(Player player) {
        //TODO pelaajan valinta UI
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data != 0) {
            GameController.getInstance().getPlayer(data).takeHeatProduction(2);
        }
        player.changeEnergyProduction(1);
        super.playWithMetadata(player, data);
    }
}
