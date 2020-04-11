package com.example.terraformingmarscompanionapp.cards.basegame.standard_projects;

import com.example.terraformingmarscompanionapp.CardSubclasses.StandardProject;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

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
