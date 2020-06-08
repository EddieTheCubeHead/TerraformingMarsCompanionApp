package com.example.terraformingmarscompanionapp.cards.corporate_era.cards;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;

public final class TechnologyDemostration extends Card {
    public TechnologyDemostration(Game game) {
        super(Type.RED, game);
        name = "Technology Demonstration";
        price = 5;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
    }

    @Override
    public void onPlay(Player player, Context context) {
        defaultEvents(player);
        EventScheduler.addEvent(new PromptEvent("Please draw 2 cards"));
        EventScheduler.playNextEvent(context);
    }
}
