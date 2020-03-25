package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class ImportedNitrogen extends Card {
    public ImportedNitrogen(Game game) {
        name = "Imported nitrogen";
        price = 23;
        tags.put("earth", 1);
        tags.put("space", 1);
        tags.put("event", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.updateManager.onSpaceEvent(player);
        player.addEventTag();
        player.changeTerraformingRating(1);
        player.changePlants(4);
        //TODO lisää 3 mikrobia ja kaksi eläintä
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
