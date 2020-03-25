package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class LunarBeam extends Card {
    public LunarBeam(Game game) {
        name = "Lunar beam";
        price = 13;
        tags.put("earth", 1);
        tags.put("energy", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addEarthTag();
        player.addEnergyTag();
        player.changeMoneyProduction(-2);
        player.changeHeatProduction(2);
        player.changeEnergyProduction(2);
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
