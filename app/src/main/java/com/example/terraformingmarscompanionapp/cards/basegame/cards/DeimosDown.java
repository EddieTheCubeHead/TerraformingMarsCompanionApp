package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class DeimosDown extends Card {
    public DeimosDown(Game game) {
        super("red");
        name = "Deimos down";
        price = 31;
        tags.add("space");
        tags.add("event");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeSteel(4);
        //TODO vähennä 8 kasvia
        owner_game.raiseTemperature(player);
        owner_game.raiseTemperature(player);
        owner_game.raiseTemperature(player);
        super.onPlay(player);
    }
}
