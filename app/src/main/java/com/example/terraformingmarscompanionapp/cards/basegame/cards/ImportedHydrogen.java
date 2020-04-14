package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class ImportedHydrogen extends Card {
    public ImportedHydrogen(Game game) {
        super("red");
        name = "Imported hyrdogen";
        price = 16;
        tags.add("earth");
        tags.add("space");
        tags.add("event");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeOcean(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
            }
        }
        //TODO kasvin, mikrobin tai eläimen lisäys
        super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        //TODO hookki tile-event pakettiin
        if (data == 0) {
            player.changePlants(3);
        } else {
            //TODO hookki resourcepakettiin
        }
        super.onPlay(player);
    }
}
