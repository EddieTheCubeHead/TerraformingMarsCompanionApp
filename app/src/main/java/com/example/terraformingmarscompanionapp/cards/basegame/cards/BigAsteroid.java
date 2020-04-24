package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

public final class BigAsteroid extends Card {
    public BigAsteroid(Game game) {
        super(Type.RED);
        name = "Big asteroid";
        price = 27;
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        Integer player_to_take_from = 0;
        //TODO UI kysy keneltä poistetaan kasveja
        //Tämän voi kutsua UI:sta
        playWithMetadata(player, player_to_take_from);

        return player_to_take_from;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data != 0) {
            GameController.getInstance().getPlayer(data).takePlants(4);
        }
        player.changeTitanium(4);
        owner_game.raiseTemperature(player);
        owner_game.raiseTemperature(player);
        super.onPlay(player);
    }
}
