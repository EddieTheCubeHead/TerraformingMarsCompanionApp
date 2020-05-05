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
        super(Type.GREEN, game);
        name = "Domed crater";
        price = 24;
        tags.add(Tag.BUILDING);
        tags.add(Tag.CITY);
        requirements.setMaxOxygen(7);
        requirements.setMinEnergyProduction(1);
        victory_points = 1;
    }

    @Override
    public void onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.CITY, owner_game));
        super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.addCity();
        player.changePlants(3);
        player.changeEnergyProduction(-1);
        player.changeMoneyProduction(3);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
