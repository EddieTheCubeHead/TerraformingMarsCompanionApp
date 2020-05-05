package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class UndergroundCity extends Card {
    public UndergroundCity(Game game) {
        super(Type.GREEN);
        name = "Underground city";
        price = 18;
        tags.add(Tag.CITY);
        tags.add(Tag.BUILDING);
        requirements.setMinEnergyProduction(2);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.CITY, owner_game));
        player.addCity();
        player.changeEnergyProduction(-2);
        player.changeSteelProduction(2);
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeEnergyProduction(-2);
        player.changeSteelProduction(2);
        super.onPlay(player);
    }
}
