package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;

public final class CloudSeeding extends Card {
    public CloudSeeding(Game game) {
        super(Type.GREEN, game);
        name = "Cloud seeding";
        price = 11;
        requirements.setMinOceans(3);
        requirements.setMinMoneyProduction(-4);
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new MetadataChoiceEvent(this));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setStealHeatProduction(1);
        production_box.setMoneyProduction(-1);
        production_box.setPlantsProduction(2);
        super.playWithMetadata(player, data);
    }
}
