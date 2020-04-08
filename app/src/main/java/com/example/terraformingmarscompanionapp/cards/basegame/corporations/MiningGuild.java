package com.example.terraformingmarscompanionapp.cards.basegame.corporations;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class MiningGuild extends Card {
    public MiningGuild(Game game) {
        name = "Mining guild";
        tags.put("building", 2);
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addBuildingTag();
        player.addBuildingTag();
        player.changeMoney(30);
        player.changeSteel(5);
        player.changeSteelProduction(1);
        player.setCorporation(this);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {
        if (owner_player == null | owner_player != player) {
            return;
        }
        owner_player.changeSteelProduction(1);
    }

    @Override
    public boolean cardAction() {
        return false;
    }
}
