package com.example.terraformingmarscompanionapp.cards.prelude.preludes;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Loan extends Card {
    public Loan(Game game) {
        super("prelude");
        name = "Loan";
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeMoneyProduction(-2);
        player.changeMoney(30);
        super.onPlay(player);
    }
}
