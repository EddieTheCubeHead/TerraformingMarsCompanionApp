package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.ActionUseEvent;
import com.example.terraformingmarscompanionapp.game.events.MetadataChoiceEvent;

public final class BiomassCombustion extends Card {
    public BiomassCombustion(Game game) {
        super(Type.GREEN, game);
        name = "Biomass combustion";
        price = 4;
        tags.add(Tag.ENERGY);
        tags.add(Tag.BUILDING);
        requirements.setMinOxygen(6);
        victory_points = -1;
    }

    @Override
    public void onPlay(Player player, Context context) {
        EventScheduler.addEvent(new ActionUseEvent());
        EventScheduler.addEvent(new MetadataChoiceEvent(this));
        EventScheduler.playNextEvent(context);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setStealPlantsProduction(1);
        production_box.setEnergyProduction(2);
        super.playWithMetadata(player, data);
    }
}
