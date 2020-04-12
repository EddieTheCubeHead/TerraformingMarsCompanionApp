package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.CardSubclasses.ActionCard;
import com.example.terraformingmarscompanionapp.CardSubclasses.Card;
import com.example.terraformingmarscompanionapp.CardSubclasses.ResourceCard;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Livestock extends ResourceCard implements ActionCard {
    public Livestock(Game game) {
        super("blue");
        name = "Livestock";
        price = 13;
        tags.add("animal");
        requirements.put("min_oxygen", 9);
        requirements.put("min_plant_production", 1);
        resource_type = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.changePlantsProduction(-1);
        player.changeMoneyProduction(2);
        super.onPlay(player);
    }

    @Override
    public boolean cardAction() {
        if (action_used) {
            return false;
        } else {
            resource_amount++;
            action_used = true;
            return true;
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

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount);
    }
}
