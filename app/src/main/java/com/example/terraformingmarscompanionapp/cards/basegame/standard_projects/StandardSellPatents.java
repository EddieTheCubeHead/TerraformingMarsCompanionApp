package com.example.terraformingmarscompanionapp.cards.basegame.standard_projects;

import com.example.terraformingmarscompanionapp.cardSubclasses.StandardProject;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class StandardSellPatents extends StandardProject {
    public StandardSellPatents(Game game) {
        super(game);
        name = "Standard project: Sell patents";
    }

    @Override
    public void onPlay(Player player) {
        Integer cards_sold = 1;
        //TODO UI kysy montako korttia myytiin
        player.changeMoney(cards_sold);
        player.changeHandSize(-cards_sold);
    }
}
