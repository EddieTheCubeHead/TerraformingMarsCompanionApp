package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.MetadataEvent;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class GiantIceAsteroid extends Card {
    public GiantIceAsteroid(Game game) {
        super(Type.RED);
        name = "Giant ice asteroid";
        price = 36;
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.OCEAN, owner_game));
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.OCEAN, owner_game));

        GameController.getInstance().addUiEvent(new MetadataEvent(MetadataEvent.MetadataType.PLAYER, this));

        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data != 0) {
            GameController.getInstance().getPlayer(data).takePlants(6);
        }
        owner_game.raiseTemperature(player);
        owner_game.raiseTemperature(player);
        super.onPlay(player);
    }
}
