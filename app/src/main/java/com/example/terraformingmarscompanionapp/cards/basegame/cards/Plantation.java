package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class Plantation extends Card {
    public Plantation(Game game) {
        super(Type.GREEN);
        name = "Plantation";
        price = 15;
        tags.add(Tag.PLANT);
        requirements.setMinScienceTags(2);
        owner_game = game;
    }

    @Override
    public Integer onPlay(Player player) {
        GameController.getInstance().addUiEvent(new TileEvent(Placeable.GREENERY, owner_game));
        player.addGreenery();
        return super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        super.onPlay(player);
    }
}
