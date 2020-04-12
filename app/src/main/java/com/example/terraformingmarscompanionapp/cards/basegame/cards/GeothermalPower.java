package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class GeothermalPower extends Card {
    public GeothermalPower(Game game) {
        super("green");
        name = "Geothermal power";
        price = 11;
        tags.add("building");
        tags.add("energy");
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(2);
        super.onPlay(player);
    }
}
