package com.example.terraformingmarscompanionapp.cards.corporate_era;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class ElectroCatapult extends Card {
    public ElectroCatapult(Game game) {
        name = "Electro catapult";
        price = 17;
        tags.put("building", 1);
        requirements.put("max_oxygen", 8);
        requirements.put("min_energy_production", 1);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addBuildingTag();
        player.addAction(this);
        player.changeEnergyProduction(-1);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        if (action_used) {
            //TODO Valitse kasvi/teräs
            return false;
        }
        else {
            owner_player.changeMoney(7);
            action_used = true;
            return true;
        }
    }
}
