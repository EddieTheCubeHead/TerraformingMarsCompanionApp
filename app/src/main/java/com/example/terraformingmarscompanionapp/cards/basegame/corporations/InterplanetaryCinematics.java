package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class InterplanetaryCinematics extends Card {
    public InterplanetaryCinematics(Game game) {
        name = "Interplanetary cinematics";
        tags.put("building", 1);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addBuildingTag();
        player.changeSteel(20);
        player.changeMoney(30);
        player.setCorporation(this);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null | owner_player != player) {
            return;
        }
        owner_player.changeMoney(2);
    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
