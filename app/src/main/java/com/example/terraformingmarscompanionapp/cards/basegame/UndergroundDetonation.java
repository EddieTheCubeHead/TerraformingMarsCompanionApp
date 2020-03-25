package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class UndergroundDetonation extends Card {
    public UndergroundDetonation(Game game) {
        name = "Underground detonation";
        price = 6;
        tags.put("building", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addBuildingTag();
        player.addAction(this);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        if (action_used) {
            return false;
        } else if (owner_player != null) {
            if (owner_player.getMoney() > 10) {
                return false;
            }
            owner_player.changeMoney(-10);
            owner_player.changeHeatProduction(2);
            action_used = true;
            return true;
        }
        return false;
    }
}
