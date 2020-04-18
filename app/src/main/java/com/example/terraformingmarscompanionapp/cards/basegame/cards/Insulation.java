package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.webSocket.events.ResourceEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.TileEventPacket;

import java.util.ArrayList;

public final class Insulation extends Card {
    public Insulation(Game game) {
        super(Type.GREEN);
        name = "Insulation";
        price = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        Integer heat_reduction_amount = 0;
        //TODO UI kysymään kuinka paljon lämmöntuotantoa poistetaan
        player.changeHeatProduction(-heat_reduction_amount);
        player.changeMoneyProduction(heat_reduction_amount);
        super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data, ArrayList<TileEventPacket> tile_events, ArrayList<ResourceEventPacket> resource_events) {
        player.changeHeatProduction(-data);
        player.changeMoneyProduction(data);
        super.onPlay(player);
    }
}
