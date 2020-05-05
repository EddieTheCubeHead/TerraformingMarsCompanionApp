package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class Mangrove extends Card {
    public Mangrove(Game game) {
        super(Type.GREEN);
        name = "Mangrove";
        price = 12;
        tags.add(Tag.PLANT);
        requirements.setMinTemperature(4);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.OCEAN, owner_game));
        owner_game.update_manager.onVpCardPlayed(player);
        player.addGreenery();
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
