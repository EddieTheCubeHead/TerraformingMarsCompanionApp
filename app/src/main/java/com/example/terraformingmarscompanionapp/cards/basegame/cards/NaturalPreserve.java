package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class NaturalPreserve extends Card {
    public NaturalPreserve(Game game) {
        super(Type.GREEN);
        name = "Natural preserve";
        price = 9;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.BUILDING);
        requirements.setMaxOxygen(4);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeNaturalReserve(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
            }
        }
        player.changeMoneyProduction(1);
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeMoneyProduction(1);
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
