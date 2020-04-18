package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class Zeppelins extends Card {
    public Zeppelins(Game game) {
        super(Type.GREEN);
        name = "Zeppelins";
        price = 13;
        requirements.setMinOxygen(5);
        victory_points = 1;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        player.changeMoneyProduction(owner_game.getCitiesOnMars());
        super.onPlay(player);
    }
}
