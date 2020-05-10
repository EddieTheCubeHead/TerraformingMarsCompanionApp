package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class Flooding extends Card {
    public Flooding(Game game) {
        super(Type.RED, game);
        name = "Flooding";
        price = 7;
        tags.add(Tag.EVENT);
        victory_points = -1;
        wait_for_server = true;
    }

    @Override
    public void onPlay(Player player) {
        GameController.addUiEvent(new TileEvent(Placeable.FLOOD_OCEAN, owner_game));
        GameController.useAction();
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        if (!(data == 0)) {
            GameController.getPlayer(data).takeMoney(4);
        }
    }
}
