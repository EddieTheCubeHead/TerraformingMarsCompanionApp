package com.example.terraformingmarscompanionapp.cards.basegame;

import com.example.terraformingmarscompanionapp.Card;
import com.example.terraformingmarscompanionapp.Game;
import com.example.terraformingmarscompanionapp.Player;

public final class SmallAnimals extends Card {
    public SmallAnimals(Game game) {
        name = "Small animals";
        price = 6;
        tags.put("animal", 1);
        requirements.put("min_oxygen", 6);
        resource_type = 2;
        owner_game = game;
    }

    @Override
    public void onPlay(Player player) {
        player.addAnimalTag();
        //TODO toiselta pelaajalta kasvien poistaminen
        player.addAction(this);
        owner_player = player;
        owner_game.updateManager.onVpCardPlayed(player);
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
        owner_player.changeVictoryPoints(resource_amount/2);
    }

}
