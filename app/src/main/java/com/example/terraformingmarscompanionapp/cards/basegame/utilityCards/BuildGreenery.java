package com.example.terraformingmarscompanionapp.cards.basegame.utilityCards;

import android.content.Context;

import com.example.terraformingmarscompanionapp.cardSubclasses.CardlikeOperation;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
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
    public void initializePlayEvents(Player player, Context context) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
        EventScheduler.addEvent(new TileEvent(Placeable.GREENERY, owner_game));
        EventScheduler.playNextEvent(context);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        Integer greenery_cost = 8 + owner_player.getModifiers().getGreeneryPlantCostModifier();
        player.getResources().setPlants(player.getResources().getPlants() - greenery_cost);
        super.playWithMetadata(player, data);
    }
}
