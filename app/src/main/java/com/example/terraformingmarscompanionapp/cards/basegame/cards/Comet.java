package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Comet extends Card {
    public Comet(Game game) {
        super(Type.RED);
        name = "Comet";
        price = 21;
        tags.add(Tag.EVENT);
        tags.add(Tag.SPACE);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        while (true) {
            if (owner_game.tile_handler.placeOcean(player)) {
                break;
            } else {
                //TODO feedback pelaajalle ja mahdollisuus perua asettaminen
            }
        }
        Integer player_to_take_from = 0;
        //TODO UI kysy keneltä poistetaan kasvit
        //Tämän voi kutsua UI:sta
        playWithMetadata(player, player_to_take_from);

        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        GameController.getInstance().getPlayer(data).takePlants(3);
        owner_game.raiseTemperature(player);
        super.onPlay(player);
    }
}
