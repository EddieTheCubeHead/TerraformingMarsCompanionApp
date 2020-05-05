package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.MetadataEvent;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class Comet extends Card {
    public Comet(Game game) {
        super(Type.RED);
        name = "Comet";
        price = 21;
        tags.add(Tag.EVENT);
        tags.add(Tag.SPACE);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.OCEAN, owner_game));
        GameController.getInstance().addUiEvent(new MetadataEvent(MetadataEvent.MetadataType.PLAYER, this));
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        GameController.getInstance().getPlayer(data).takePlants(3);
        owner_game.raiseTemperature(player);
    }
}
