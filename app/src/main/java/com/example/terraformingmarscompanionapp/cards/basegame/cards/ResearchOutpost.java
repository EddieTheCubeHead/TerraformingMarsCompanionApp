package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class ResearchOutpost extends Card {
    public ResearchOutpost(Game game) {
        super(Type.BLUE);
        name = "Research outpost";
        price = 18;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.BUILDING);
        tags.add(Tag.CITY);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeResearchOutpost(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
            }
        }
        player.addCity();
        player.changeCardDiscount(1);
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeCardDiscount(1);
        super.onPlay(player);
    }
}
