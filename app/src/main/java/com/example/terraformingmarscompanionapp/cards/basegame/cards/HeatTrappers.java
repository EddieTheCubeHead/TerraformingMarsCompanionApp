package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.webSocket.ServerGameController;

public final class HeatTrappers extends Card {
    public HeatTrappers(Game game) {
        super(Type.GREEN);
        name = "Heat trappers";
        price = 6;
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
        victory_points = -1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        //TODO poista toiselta kaksi lämpöä
        player.changeEnergyProduction(1);
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.getPlayer(ServerGameController.getPlayerName(data)).changeHeat(-2);
        player.changeEnergyProduction(1);
        super.onPlay(player);
    }
}
