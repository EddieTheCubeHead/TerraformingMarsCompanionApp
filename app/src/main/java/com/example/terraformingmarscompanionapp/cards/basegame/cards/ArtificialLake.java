package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class ArtificialLake extends Card {
    public ArtificialLake(Game game) {
        super(Type.GREEN, game);
        name = "Artificial lake";
        price = 15;
        tags.add(Tag.BUILDING);
        requirements.setMinTemperature(-6);
        victory_points = 1;
        wait_for_server = true;
    }

    @Override
    public void onPlay(Player player) {
        GameController.addUiEvent(new TileEvent(Placeable.LAND_OCEAN, owner_game));
        super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
