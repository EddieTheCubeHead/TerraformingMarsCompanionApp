package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
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
        owner_game.raiseTemperature(player);
        owner_game.raiseTemperature(player);
        player.changeTitanium(4);
        //TODO poista 4 kasvia muilta
        return super.onPlay(player);
    }
}
