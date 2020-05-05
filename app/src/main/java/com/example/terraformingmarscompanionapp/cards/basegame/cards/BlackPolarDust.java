package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class BlackPolarDust extends Card {
    public BlackPolarDust(Game game) {
        super(Type.GREEN, game);
        name = "Black polar dust";
        price = 15;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.OCEAN, owner_game));
        super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeMoneyProduction(-2);
        player.changeHeatProduction(3);
        super.playWithMetadata(player, data);
    }
}
