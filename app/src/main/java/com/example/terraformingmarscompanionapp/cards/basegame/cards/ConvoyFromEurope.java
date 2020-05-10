package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.PromptEvent;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class ConvoyFromEurope extends Card {
    public ConvoyFromEurope(Game game) {
        super(Type.RED, game);
        name = "Convoy from europe";
        price = 15;
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
        wait_for_server = true;
    }

    @Override
    public void onPlay(Player player) {
        GameController.addUiEvent(new TileEvent(Placeable.OCEAN, owner_game));
        GameController.addUiEvent(new PromptEvent("Please draw a card"));
        super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        player.changeHandSize(1);
        super.playWithMetadata(player, data);
    }
}
