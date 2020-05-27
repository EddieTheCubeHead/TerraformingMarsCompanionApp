package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class InvestmentLoan extends Card {
    public InvestmentLoan(Game game) {
        super(Type.RED, game);
        name = "Investment loaan";
        price = 3;
        tags.add(Tag.EARTH);
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeMoneyProduction(-1);
        player.changeMoney(10);
        super.playWithMetadata(player, data);
    }
}
