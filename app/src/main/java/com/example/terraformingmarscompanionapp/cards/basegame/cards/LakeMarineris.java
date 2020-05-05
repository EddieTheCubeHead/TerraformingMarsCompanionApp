package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class LakeMarineris extends Card {
    public LakeMarineris(Game game) {
        super(Type.GREEN);
        name = "Lake marineris";
        price = 18;
        requirements.setMinTemperature(0);
        victory_points = 2;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.OCEAN, owner_game));
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.OCEAN, owner_game));
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
