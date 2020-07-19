package com.example.terraformingmarscompanionapp.cards.prelude.preludes;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
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
        player.getResources().setMoneyProduction(player.getResources().getMoneyProduction() - 2);
        player.getResources().setMoney(player.getResources().getMoney() + 30);
        super.playWithMetadata(player, data);
    }
}
