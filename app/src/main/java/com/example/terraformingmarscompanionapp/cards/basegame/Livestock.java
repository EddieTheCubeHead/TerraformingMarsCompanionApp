package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Livestock extends Card {
    public Livestock(Game game) {
        name = "Livestock";
        price = 13;
        tags.put("animal", 1);
        requirements.put("min_oxygen", 9);
        requirements.put("min_plant_production", 1);
        resource_type = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addAnimalTag();
        player.changePlantsProduction(-1);
        player.changeMoneyProduction(2);
        player.addAction(this);
        player.addGreen(this);
        owner_player = player;
    }

    @Override
    public void cardEffect(Player player) {

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
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount);
    }
}
