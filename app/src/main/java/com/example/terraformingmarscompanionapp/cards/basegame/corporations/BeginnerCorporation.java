package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class BeginnerCorporation extends Card {
    public BeginnerCorporation(Game game) {
        super(Type.CORPORATION, game);
        name = "Beginner corporation";
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getResources().setMoney(42);
        super.playWithMetadata(player, data);
    }
}
