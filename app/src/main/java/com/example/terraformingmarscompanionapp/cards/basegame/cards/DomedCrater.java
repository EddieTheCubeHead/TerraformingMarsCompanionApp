package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class DomedCrater extends Card {
    public DomedCrater(Game game) {
        super(Type.GREEN);
        name = "Domed crater";
        price = 24;
        tags.add(Tag.BUILDING);
        tags.add(Tag.CITY);
        requirements.setMaxOxygen(7);
        requirements.setMinEnergyProduction(1);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.CITY, owner_game));

        player.addCity();
        player.changePlants(3);
        player.changeEnergyProduction(-1);
        player.changeMoneyProduction(3);
        owner_game.update_manager.onVpCardPlayed(player);
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changePlants(3);
        player.changeEnergyProduction(-1);
        player.changeMoneyProduction(3);
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }
}
