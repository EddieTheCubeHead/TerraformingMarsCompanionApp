package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class SpaceMirrors extends Card {
    public SpaceMirrors(Game game) {
        name = "Space mirrors";
        price = 3;
        tags.put("energy", 1);
        tags.put("space", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addEnergyTag();
        player.addSpaceTag();
        player.addAction(this);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        if (owner_player.getMoney() < 7) {
            return false;
        } else {
            owner_player.changeMoney(-7);
            owner_player.changeEnergyProduction(1);
            return true;
        }
    }
}
