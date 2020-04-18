package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.webSocket.events.ResourceEventPacket;
import com.example.terraformingmarscompanionapp.webSocket.events.TileEventPacket;

import java.util.ArrayList;

public final class LocalHeatTrapping extends Card {
    public LocalHeatTrapping(Game game) {
        super("red");
        name = "Local heat trapping";
        price = 1;
        tags.add("event");
        requirements.setMinHeat(5);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeHeat(-5);
        boolean added_animals = true;
        //TODO UI kysy kasvien vai eläinten lisäys, jos eläimet, minne?
        if (added_animals) {
            //TODO lisää eläimet haluttuun paikkaan
        } else {
            player.changePlants(4);
        }
        super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data, ArrayList<TileEventPacket> tile_events, ArrayList<ResourceEventPacket> resource_events) {
        player.changeHeat(-5);
        if (data == 0) {
            player.changePlants(4);
        } else {
            //TODO hookki resourcepakettiin
        }
    }
}
