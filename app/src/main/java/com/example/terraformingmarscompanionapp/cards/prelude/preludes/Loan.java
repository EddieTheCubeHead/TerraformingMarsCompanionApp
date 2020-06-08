package com.example.terraformingmarscompanionapp.cards.prelude.preludes;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Loan extends Card {
    public Loan(Game game) {
        super(Type.PRELUDE, game);
        name = "Loan";
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeMoneyProduction(-2);
        player.changeMoney(30);
        super.playWithMetadata(player, data);
    }
}
