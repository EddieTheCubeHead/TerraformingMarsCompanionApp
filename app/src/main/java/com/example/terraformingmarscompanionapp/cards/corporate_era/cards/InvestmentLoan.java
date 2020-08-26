package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import com.example.terraformingmarscompanionapp.exceptions.InvalidResourcesException;
import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Tag;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class InvestmentLoan extends Card {
    public InvestmentLoan() {
        super(Type.RED);
        name = "Investment loaan";
        price = 3;
        tags.add(Tag.EARTH);
        tags.add(Tag.EVENT);
        requirements.setMinMoneyProduction(-4);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) throws InvalidResourcesException {
        player.getResources().setMoneyProduction(player.getResources().getMoneyProduction() - 1);
        player.getResources().setMoney(player.getResources().getMoney() + 10);
        super.playWithMetadata(player, data);
    }
}
