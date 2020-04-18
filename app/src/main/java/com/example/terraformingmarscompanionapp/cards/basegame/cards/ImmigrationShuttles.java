package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.cardSubclasses.Tag;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class ImmigrationShuttles extends Card {
    public ImmigrationShuttles(Game game) {
        super(Type.GREEN);
        name = "Immigration shuttles";
        price = 31;
        tags.add(Tag.EARTH);
        tags.add(Tag.SPACE);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeMoneyProduction(5);
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints((owner_game.getCitiesOnMars() + owner_game.getCitiesInSpace())/3);
    }
}
