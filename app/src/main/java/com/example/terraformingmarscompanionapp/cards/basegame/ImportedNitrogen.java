package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class ImportedNitrogen extends Card {
    public ImportedNitrogen(Game game) {
        super("red");
        name = "Imported nitrogen";
        price = 23;
        tags.add("earth");
        tags.add("space");
        tags.add("event");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeTerraformingRating(1);
        player.changePlants(4);
        //TODO lisää 3 mikrobia ja kaksi eläintä
        super.onPlay(player);
    }
}
