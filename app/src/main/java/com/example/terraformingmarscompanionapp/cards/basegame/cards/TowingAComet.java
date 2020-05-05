package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class TowingAComet extends Card {
    public TowingAComet(Game game) {
        super(Type.RED);
        name = "Towing a comet";
        price = 23;
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.OCEAN, owner_game));
        player.changePlants(2);
        owner_game.raiseOxygen(player);
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changePlants(2);
        owner_game.raiseOxygen(player);
        super.onPlay(player);
    }
}
