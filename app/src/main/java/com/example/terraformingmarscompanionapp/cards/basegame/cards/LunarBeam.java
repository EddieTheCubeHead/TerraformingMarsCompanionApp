package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class LunarBeam extends Card {
    public LunarBeam(Game game) {
        super("green");
        name = "Lunar beam";
        price = 13;
        tags.add("earth");
        tags.add("energy");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeMoneyProduction(-2);
        player.changeHeatProduction(2);
        player.changeEnergyProduction(2);
        super.onPlay(player);
    }
}
