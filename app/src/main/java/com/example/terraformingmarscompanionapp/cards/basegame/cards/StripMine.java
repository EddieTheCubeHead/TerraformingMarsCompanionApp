package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class StripMine extends Card {
    public StripMine(Game game) {
        super("green");
        name = "Strip mine";
        price = 25;
        tags.add("buiding");
        requirements.setMinEnergyProduction(2);
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
