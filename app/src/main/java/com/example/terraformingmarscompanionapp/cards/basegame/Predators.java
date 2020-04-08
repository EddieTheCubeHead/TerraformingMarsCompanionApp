package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.CardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

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

    public String getActionName() {
        return getName();
    }

    public Boolean getActionUsed() {
        return action_used;
    }
}
