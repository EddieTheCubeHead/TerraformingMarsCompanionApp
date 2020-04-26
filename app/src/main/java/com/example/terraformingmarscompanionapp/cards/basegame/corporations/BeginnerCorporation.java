package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class BeginnerCorporation extends Card {
    public BeginnerCorporation(Game game) {
        super(Type.CORPORATION);
        name = "Beginner corporation";
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        player.changeMoney(42);
        //TODO beginner corporationin erikoishommat
        return 0;
    }
}
