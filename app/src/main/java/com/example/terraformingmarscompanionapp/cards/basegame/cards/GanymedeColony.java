package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class GanymedeColony extends Card {
    public GanymedeColony(Game game) {
        super(Type.GREEN);
        name = "Ganymede colony";
        price = 20;
        tags.add(Tag.SPACE);
        tags.add(Tag.JOVIAN);
        tags.add(Tag.CITY);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        owner_game.tile_handler.placeGanymede(player);
        player.addCity();
        super.onPlay(player);
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(owner_player.getJovianTags());
    }
}
