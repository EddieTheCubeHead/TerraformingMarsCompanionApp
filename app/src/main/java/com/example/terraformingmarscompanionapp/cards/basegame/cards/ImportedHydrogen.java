package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class ImportedHydrogen extends Card {
    public ImportedHydrogen(Game game) {
        super(Type.RED);
        name = "Imported hydrogen";
        price = 16;
        tags.add(Tag.EARTH);
        tags.add(Tag.SPACE);
        tags.add(Tag.EVENT);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.OCEAN, owner_game));
        Integer choice = 0;
        //TODO UI kasvin (0), mikrobin (1) tai eläimen (2) lisäys
        playWithMetadata(player, choice);

        return choice;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (data == 0) {
            player.changePlants(3);
        }
        super.onPlay(player);
    }
}
