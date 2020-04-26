package com.example.terraformingmarscompanionapp.cards.basegame.standard_projects;

import com.example.terraformingmarscompanionapp.cardSubclasses.StandardProject;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class StandardSellPatents extends StandardProject {
    public StandardSellPatents(Game game) {
        super(game);
        name = "Standard project: Sell patents";
    }

    @Override
    public Integer onPlay(Player player) {
        Integer cards_sold = 1;
        //TODO UI kysy montako korttia myytiin
        playWithMetadata(player, cards_sold);

        return cards_sold;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeMoney(data);
        player.changeHandSize(-data);
    }
}
