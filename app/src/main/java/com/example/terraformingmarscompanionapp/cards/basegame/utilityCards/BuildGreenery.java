package com.example.terraformingmarscompanionapp.cards.basegame.utilityCards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceStandardOperation;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class BuildGreenery extends ResourceStandardOperation {
    public BuildGreenery(Game game) {
        super(game);
        name = "Build greenery";
        requirements.setPlantsForGreenery(true);
        wait_for_server = true;
    }

    @Override
    public void onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.GREENERY, owner_game));
        super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.raiseOxygen(player);
        player.changePlants(8 + player.getGreeneryPlantCostModifier());
        super.playWithMetadata(player, data);
    }
}
