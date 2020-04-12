package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class ImportedGHG extends Card {
    public ImportedGHG(Game game) {
        super("red");
        name = "Imported GHG";
        price = 7;
        tags.add("earth");
        tags.add("space");
        tags.add("event");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeHeatProduction(1);
        player.changeHeat(3);
        super.onPlay(player);
    }
}
