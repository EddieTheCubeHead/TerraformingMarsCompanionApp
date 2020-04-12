package com.example.terraformingmarscompanionapp.cards.corporate_era;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.Card;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class ElectroCatapult extends Card implements ActionCard {
    public ElectroCatapult(Game game) {
        super("blue");
        name = "Electro catapult";
        price = 17;
        tags.add("building");
        requirements.put("max_oxygen", 8);
        requirements.put("min_energy_production", 1);
        victory_points = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changeEnergyProduction(-1);
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }

    public boolean cardAction() {
        if (action_used) {
            return false;
        }
        else {
            //TODO Valitse kasvi/teräs
            owner_player.changeMoney(7);
            action_used = true;
            return true;
        }
    }

    public String getActionName() {
        return getName();
    }

    public Boolean getActionUsed() {
        return action_used;
    }
}
