package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class StripMine extends Card {
    public StripMine(Game game) {
        super("green");
        name = "Strip mine";
        price = 25;
        tags.add("buiding");
        requirements.put("min_energy", 2);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(-2);
        player.changeSteelProduction(2);
        player.changeTitaniumProduction(1);
        owner_game.raiseOxygen(player);
        owner_game.raiseOxygen(player);
        super.onPlay(player);
    }
}
