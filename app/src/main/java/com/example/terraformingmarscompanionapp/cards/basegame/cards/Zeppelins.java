package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.game.cardClasses.Card;
import com.example.terraformingmarscompanionapp.game.cardClasses.Type;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.player.Player;

public final class Zeppelins extends Card {
    public Zeppelins() {
        super(Type.GREEN);
        name = "Zeppelins";
        price = 13;
        requirements.setMinOxygen(5);
        victory_points = 1;
    }

    @Override
    public void playWithMetadata(Player player, Integer data) {
        game.update_manager.onVpCardPlayed(player);
        production_box.setMoneyProduction(game.getCitiesOnMars());
        super.playWithMetadata(player, data);
    }

    @Override
    public void playProductionBox() {
        production_box.setMoneyProduction(game.getCitiesOnMars());
        super.playProductionBox();
    }
}
