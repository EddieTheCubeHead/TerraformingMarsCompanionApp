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
        super(Type.GREEN);
        name = "Artificial lake";
        price = 15;
        tags.add(Tag.BUILDING);
        requirements.setMinTemperature(-6);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.LAND_OCEAN, owner_game));
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }
}
