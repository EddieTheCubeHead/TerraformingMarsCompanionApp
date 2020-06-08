package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;

public final class Research extends Card {
    public Research(Game game) {
        super(Type.GREEN, game);
        name = "Research";
        price = 11;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.SCIENCE);
        victory_points = 1;
    }

    @Override
    public void onPlay(Player player, Context context) {
        defaultEvents(player);
        EventScheduler.addEvent(new PromptEvent("Please draw 2 cards"));
        EventScheduler.playNextEvent(context);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        player.changeHandSize(2);
        super.playWithMetadata(player, data);
    }
}
