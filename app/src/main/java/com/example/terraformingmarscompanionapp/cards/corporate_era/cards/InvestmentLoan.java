package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class InvestmentLoan extends Card {
    public InvestmentLoan(Game game) {
        super(Type.RED, game);
        name = "Investment loaan";
        price = 3;
        tags.add(Tag.EARTH);
        tags.add(Tag.EVENT);
        owner_game = game;
        requirements.setMinMoneyProduction(-4);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getResources().setMoneyProduction(player.getResources().getMoneyProduction() - 1);
        player.getResources().setMoney(player.getResources().getMoney() + 10);
        super.playWithMetadata(player, data);
    }
}
