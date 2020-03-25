package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class ImportedGHG extends Card {
    public ImportedGHG(Game game) {
        name = "Imported GHG";
        price = 7;
        tags.put("earth", 1);
        tags.put("space", 1);
        tags.put("event", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.updateManager.onSpaceEvent(player);
        player.addEventTag();
        player.changeHeatProduction(1);
        player.changeHeat(3);
        player.addRed(this);
    owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
