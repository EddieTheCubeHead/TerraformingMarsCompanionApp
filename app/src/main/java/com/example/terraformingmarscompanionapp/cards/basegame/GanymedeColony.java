package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class GanymedeColony extends Card {
    public GanymedeColony(Game game) {
        super("green");
        name = "Ganymede colony";
        price = 20;
        tags.add("space");
        tags.add("jovian");
        tags.add("city");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        owner_game.tile_handler.placeGanymede(player);
        super.onPlay(player);
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(owner_player.getJovianTags());
    }
}
