package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;

public final class LagrangeObservatory extends Card {
    public LagrangeObservatory(Game game) {
        super(Type.GREEN, game);
        name = "Lagrange obsrevatory";
        price = 9;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.SPACE);
        victory_points = 1;
    }

    @Override
    public void onPlay(Player player, Context context) {
        defaultEvents(player);
        EventScheduler.addEvent(new PromptEvent("Please draw a card"));
        EventScheduler.playNextEvent(context);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
