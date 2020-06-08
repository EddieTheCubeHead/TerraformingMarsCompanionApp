package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;

public final class InvestionContest extends Card {
    public InvestionContest(Game game) {
        super(Type.RED, game);
        name = "Investion contest";
        price = 2;
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player, Context context) {
        defaultEvents(player);
        EventScheduler.addEvent(new PromptEvent("Please look 3 top cards on draw pile and choose 1 of them"));
        EventScheduler.playNextEvent(context);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeHandSize(1);
        super.playWithMetadata(player, data);
    }
}
