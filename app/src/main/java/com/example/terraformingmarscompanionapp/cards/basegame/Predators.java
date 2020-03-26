package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class Predators extends Card {
    public Predators(Game game) {
        name = "Predators";
        price = 14;
        tags.put("animal", 1);
        requirements.put("min_oxygen", 11);
        resource_type = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        owner_game.updateManager.onVpCardPlayed(player);
        player.addAnimalTag();
        player.addAction(this);
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
            //TODO poista toiselta eläin
            resource_amount++;
            return true;
        }
    }

    @Override
    public void onGameEnd() {
        owner_player.changeVictoryPoints(resource_amount);
    }
}
