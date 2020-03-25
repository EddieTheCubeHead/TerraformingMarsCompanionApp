package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class SearchForLife extends Card {
    public SearchForLife(Game game) {
        name = "Search for life";
        price = 3;
        tags.put("science", 1);
        requirements.put("max_oxygen", 6);
        resource_type = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addScienceTag();
        player.addAction(this);
        owner_game.updateManager.onVpCardPlayed(player);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

    }

    @Override
    public boolean cardAction() {
        if (action_used) {
            return false;
        } else if (owner_player.getMoney() < 1) {
            return false;
        } else {
            owner_player.changeMoney(-1);
            boolean found_life = false;
            //TODO UI prompt tuliko mikrobi
            if (found_life) {
                resource_amount++;
            }
            return true;
        }
    }

    @Override
    public void onGameEnd() {
        if (resource_amount < 0) {
            owner_player.changeVictoryPoints(3);
        }
    }
}
