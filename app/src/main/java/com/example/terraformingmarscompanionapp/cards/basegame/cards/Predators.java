package com.example.terraformingmarscompanionapp.cards.basegame.cards;

import com.example.terraformingmarscompanionapp.cardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.cardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.gameElements.Game;
import com.example.terraformingmarscompanionapp.gameElements.Player;

public final class Predators extends ResourceCard implements ActionCard {
    public Predators(Game game) {
        super("blue");
        name = "Predators";
        price = 14;
        tags.add("animal");
        requirements.put("min_oxygen", 11);
        resource_type = 2;
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
        } else {
            //TODO poista toiselta eläin
            resource_amount++;
            return true;
        }
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount);
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
