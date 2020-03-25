package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class StripMine extends Card {
    public StripMine(Game game) {
        name = "Strip mine";
        price = 25;
        tags.put("buiding", 1);
        requirements.put("min_energy", 2);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addBuildingTag();
        player.changeEnergyProduction(-2);
        player.changeSteelProduction(2);
        player.changeTitaniumProduction(1);
        owner_game.raiseOxygen(player);
        owner_game.raiseOxygen(player);
        player.addGreen(this);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
