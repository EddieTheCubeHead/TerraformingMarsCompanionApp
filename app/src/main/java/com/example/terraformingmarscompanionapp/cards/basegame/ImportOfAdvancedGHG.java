package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class ImportOfAdvancedGHG extends Card {
    public ImportOfAdvancedGHG(Game game) {
        super("red");
        name = "Import of advanced GHG";
        price = 9;
        tags.add("space");
        tags.add("earth");
        tags.add("event");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeHeatProduction(2);
        super.onPlay(player);
    }
}
