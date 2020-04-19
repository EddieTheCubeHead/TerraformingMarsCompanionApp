package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class NuclearZone extends Card {
    public NuclearZone(Game game) {
        super(Type.GREEN);
        name = "Nuclear zone";
        price = 10;
        tags.add(Tag.EARTH);
        victory_points = -2;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeNuclearZone(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollinen peruminen
            }
        }
        owner_game.raiseTemperature(player);
        owner_game.raiseTemperature(player);
        return super.onPlay(player);
    }
}
