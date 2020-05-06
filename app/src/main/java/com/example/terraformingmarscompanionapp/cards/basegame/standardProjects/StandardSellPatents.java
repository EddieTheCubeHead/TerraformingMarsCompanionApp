package com.example.terraformingmarscompanionapp.cards.basegame.standardProjects;

import com.example.terraformingmarscompanionapp.cardSubclasses.StandardProject;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class StandardSellPatents extends StandardProject {
    public StandardSellPatents(Game game) {
        super(game);
        name = "Standard project: Sell patents";
    }

    @Override
    public void onPlay(Player player) {
        //TODO numeron valinta UI
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeMoney(data);
        player.changeHandSize(-data);
    }
}
