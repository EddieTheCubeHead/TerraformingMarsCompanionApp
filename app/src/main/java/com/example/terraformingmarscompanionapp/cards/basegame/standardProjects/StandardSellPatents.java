package com.example.terraformingmarscompanionapp.cards.basegame.standardProjects;

import com.example.terraformingmarscompanionapp.game.cardClasses.StandardProject;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.MetadataIntegerEvent;

public final class StandardSellPatents extends StandardProject {
    public StandardSellPatents() {
        super();
        name = "Standard project: Sell patents";
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new MetadataIntegerEvent("Amount of cards stolen:", 0, player.getHandSize(), this));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.getResources().setMoney(player.getResources().getMoney() + data);
        player.changeHandSize(-data);
    }
}
