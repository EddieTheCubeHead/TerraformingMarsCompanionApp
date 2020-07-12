package com.example.terraformingmarscompanionapp.cards.basegame.utilityCards;

import com.example.terraformingmarscompanionapp.cardSubclasses.CardlikeOperation;
import com.example.terraformingmarscompanionapp.game.EventScheduler;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.events.PlayCardEvent;
import com.example.terraformingmarscompanionapp.game.player.Player;
import com.example.terraformingmarscompanionapp.game.events.TileChoiceEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class BuildGreenery extends CardlikeOperation {
    public BuildGreenery(Game game) {
        super(game);
        name = "Build greenery";
        requirements.setPlantsForGreenery(true);
    }

    @Override
    public void initializePlayEvents(Player player) {
        EventScheduler.addEvent(new PlayCardEvent(this, player, 0));
        EventScheduler.addEvent(new TileChoiceEvent(Placeable.GREENERY, owner_game));
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        Integer greenery_cost = 8 + player.getModifiers().getGreeneryPlantCostModifier();
        player.getResources().setPlants(player.getResources().getPlants() - greenery_cost);
        super.playWithMetadata(player, data);
    }
}
