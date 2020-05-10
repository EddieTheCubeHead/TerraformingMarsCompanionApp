package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.GameController;
import com.example.terraformingmarscompanionapp.game.Player;
import com.example.terraformingmarscompanionapp.game.events.TileEvent;
import com.example.terraformingmarscompanionapp.game.tileSystem.Placeable;

public final class NaturalPreserve extends Card {
    public NaturalPreserve(Game game) {
        super(Type.GREEN, game);
        name = "Natural preserve";
        price = 9;
        tags.add(Tag.SCIENCE);
        tags.add(Tag.BUILDING);
        requirements.setMaxOxygen(4);
        victory_points = 1;
        wait_for_server = true;
    }

    @Override
    public void onPlay(Player player) {
        GameController.addUiEvent(new TileEvent(Placeable.NATURAL_RESERVE, owner_game));
        super.onPlay(player);
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        production_box.setMoneyProduction(1);
        owner_game.update_manager.onVpCardPlayed(player);
        super.playWithMetadata(player, data);
    }
}
