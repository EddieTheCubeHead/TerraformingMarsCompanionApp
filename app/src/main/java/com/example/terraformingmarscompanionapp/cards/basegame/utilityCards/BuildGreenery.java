package com.example.terraformingmarscompanionapp.cards.basegame.utilityCards;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cardSubclasses.CardlikeOperation;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class BuildGreenery extends CardlikeOperation {
    public BuildGreenery(Game game) {
        super(game);
        name = "Build greenery";
        requirements.setPlantsForGreenery(true);
    }

    @Override
    public void onPlay(Player player, Context context) {
        defaultEvents(player);
        EventScheduler.addEvent(new TileEvent(Placeable.GREENERY, owner_game));
        EventScheduler.playNextEvent(context);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.takePlants(8 + player.getGreeneryPlantCostModifier());
        super.playWithMetadata(player, data);
    }
}
