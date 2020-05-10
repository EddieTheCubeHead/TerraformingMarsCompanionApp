package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class OpenCity extends Card {
    public OpenCity(Game game) {
        super(Type.GREEN, game);
        name = "Open city";
        price = 23;
        tags.add(Tag.BUILDING);
        tags.add(Tag.CITY);
        requirements.setMinOxygen(12);
        requirements.setMinEnergyProduction(1);
        victory_points = 1;
        owner_game = game;
        wait_for_server = true;
    }

    @Override
    public void onPlay(Player player) {
        GameController.addUiEvent(new TileEvent(Placeable.CITY, owner_game));
        super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setEnergyProduction(-1);
        production_box.setMoneyProduction(4);
        player.changePlants(2);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
