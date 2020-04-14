package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.MetadataAction;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.game.Game;
import com.example.terraformingmarscompanionapp.game.Player;

public final class SearchForLife extends ResourceCard implements MetadataAction {
    public SearchForLife(Game game) {
        super("blue");
        name = "Search for life";
        price = 3;
        tags.add("science");
        requirements.setMaxOxygen(6);
        resource_type = 1;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
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
    public boolean actionWithMetadata(Integer data) {
        if (action_used) {
            return false;
        } else if (owner_player.getMoney() < 1) {
            return false;
        } else {
            owner_player.changeMoney(-1);
            if (data >= 0) {
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

    @Override
    public String getActionName() {
        return getName();
    }

    @Override
    public Boolean getActionUsed() {
        return action_used;
    }
}
