package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.CardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class SearchForLife extends ResourceCard implements ActionCard {
    public SearchForLife(Game game) {
        super("blue");
        name = "Search for life";
        price = 3;
        tags.add("science");
        requirements.put("max_oxygen", 6);
        resource_type = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }

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

    public void onGameEnd() {
        if (resource_amount < 0) {
            owner_player.changeVictoryPoints(3);
        }
    }

    public String getActionName() {
        return getName();
    }

    public Boolean getActionUsed() {
        return action_used;
    }
}
