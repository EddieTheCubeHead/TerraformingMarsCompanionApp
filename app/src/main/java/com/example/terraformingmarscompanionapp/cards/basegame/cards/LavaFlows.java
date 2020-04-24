package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class LavaFlows extends Card {
    public LavaFlows(Game game) {
        super(Type.RED);
        name = "Lava flows";
        price = 18;
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeLavaFlow(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja virheenhallinta jos jokainen volcanic-tiili varattu
            }
        }
        owner_game.raiseTemperature(player);
        owner_game.raiseTemperature(player);
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.raiseTemperature(player);
        owner_game.raiseTemperature(player);
        super.onPlay(player);
    }
}
