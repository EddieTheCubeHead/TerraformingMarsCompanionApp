package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class ImportedHydrogen extends Card {
    public ImportedHydrogen(Game game) {
        name = "Imported hyrdogen";
        price = 16;
        tags.put("earth", 1);
        tags.put("space", 1);
        tags.put("event", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addEventTag();
        //TODO kasvin, mikrobin tai eläimen lisäys
        owner_game.updateManager.onSpaceEvent(player);
        owner_game.placeOcean(player, false);
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
