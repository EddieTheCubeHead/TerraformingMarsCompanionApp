package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.CardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Fish extends ResourceCard implements ActionCard {
    public Fish(Game game) {
        super("blue");
        name = "Fish";
        price = 9;
        tags.add("animal");
        requirements.put("min_temperature", 2);
        resource_type = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        //TODO toiselta kasvintuotannon ottaminen
        owner_game.update_manager.onVpCardPlayed(player);
        super.onPlay(player);
    }

    public boolean cardAction() {
        if (action_used) {
            return false;
        } else {
            resource_amount++;
            action_used = true;
            return true;
        }
    }

    public String getActionName() {
        return getName();
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount);
    }

    public Boolean getActionUsed() {
        return action_used;
    }
}
