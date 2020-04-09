package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class ResearchOutpost extends Card {
    public ResearchOutpost(Game game) {
        super("blue");
        name = "Research outpost";
        price = 18;
        tags.add("science");
        tags.add("building");
        tags.add("city");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeResearchOutpost(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
            }
        }
        player.changeCardDiscount(1);
        super.onPlay(player);
    }
}
