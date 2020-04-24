package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Asteroid extends Card {
    public Asteroid(Game game) {
        super(Type.RED);
        name = "Asteroid";
        price = 14;
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        owner_game.raiseTemperature(player);
        player.changeTitanium(2);
        //TODO poista toiselta 3 kasvia
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data != 0) {
           GameController.getInstance().getPlayer(data).changePlants(3);
        }
        owner_game.raiseTemperature(player);
        player.changeTitanium(2);
        super.onPlay(player);
    }
}
