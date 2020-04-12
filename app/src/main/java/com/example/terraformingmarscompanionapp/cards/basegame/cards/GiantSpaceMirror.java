package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class GiantSpaceMirror extends Card {
    public GiantSpaceMirror(Game game) {
        super("green");
        name = "Giant space mirror";
        price = 17;
        tags.add("energy");
        tags.add("space");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(3);
        super.onPlay(player);
    }
}
