package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class GanymedeColony extends Card {
    public GanymedeColony(Game game) {
        name = "Ganymede colony";
        price = 20;
        tags.put("space", 1);
        tags.put("jovian", 1);
        tags.put("city", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addGreen(this);
        owner_game.updateManager.onVpCardPlayed(player);
        owner_player = player;
        owner_game.tile_handler.placeGanymede(player);
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(owner_player.getJovianTags());
    }
}
