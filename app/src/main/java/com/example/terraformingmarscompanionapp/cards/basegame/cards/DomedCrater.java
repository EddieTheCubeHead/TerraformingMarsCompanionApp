package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.cardSubclasses.Type;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class DomedCrater extends Card {
    public DomedCrater(Game game) {
        super(Type.GREEN, game);
        name = "Domed crater";
        price = 24;
        tags.add(Tag.BUILDING);
        tags.add(Tag.CITY);
        requirements.setMaxOxygen(7);
        requirements.setMinEnergyProduction(1);
        victory_points = 1;
    }

    @Override
    public void onPlay(Player player, Context context) {
        defaultEvents(player);
        EventScheduler.addEvent(new TileEvent(Placeable.CITY, owner_game));
        EventScheduler.playNextEvent(context);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changePlants(3);
        production_box.setEnergyProduction(-1);
        production_box.setMoneyProduction(3);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
