package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class BeginnerCorporation extends Card {
    public BeginnerCorporation(Game game) {
        super(Type.CORPORATION, game);
        name = "Beginner corporation";
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeMoney(42);
        //TODO beginner corporationin erikoishommat
        super.playWithMetadata(player, data);
    }
}
