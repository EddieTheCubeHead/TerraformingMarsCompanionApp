package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class MartianRails extends Card {
    public MartianRails(Game game) {
        name = "Martian rails";
        price = 13;
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
        if (!owner_player.changeEnergy(-1) | action_used) {
            return false;
        } else {
            owner_player.changeMoney(owner_game.getCitiesOnMars());
            action_used = true;
            return true;
        }
    }
}
