package com.example.terraformingmarscompanionapp.cards.basegame.standardProjects;

import android.content.Context;
import android.content.Intent;

import com.example.terraformingmarscompanionapp.cardSubclasses.StandardProject;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataIntegerEvent;
import com.example.terraformingmarscompanionapp.ui.playDialogues.IntegerDialog;

public final class StandardSellPatents extends StandardProject {
    public StandardSellPatents(Game game) {
        super(game);
        name = "Standard project: Sell patents";
    }

    @Override
    public void onPlay(Player player, Context context) {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new MetadataIntegerEvent("Amount of cards stolen:", 0, player.getHandSize(), this));
        EventScheduler.playNextEvent(context);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeMoney(data);
        player.changeHandSize(-data);
    }
}
